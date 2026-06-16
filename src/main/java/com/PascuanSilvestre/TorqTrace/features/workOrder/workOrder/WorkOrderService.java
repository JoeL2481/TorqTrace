package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.common.exception.IncoherentDataException;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartService;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.MaintenanceService;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.CompleteWorkOrderDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper.WorkOrderMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.WorkshopStockEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.WorkshopStockService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkOrderService implements IWorkOrderService<WorkOrderCreateDTO, WorkOrderUpdateDTO, WorkOrderResponseDTO,Long> {

    private final WorkOrderMapper workOrderMapper;
    private final WorkOrderRepository workOrderRepository;
    private final WorkshopPermissionService workshopPermissionService;
    private final WorkShopService workShopService;
    private final UserVehicleService userVehicleService;
    private final SparePartService sparePartService;
    private final WorkshopStockService workshopStockService;
    private final WorkOrderItemService workOrderItemService;
    private final MaintenanceService maintenanceService;

    @Transactional
    @Override
    public WorkOrderResponseDTO create(WorkOrderCreateDTO request) {

        workshopPermissionService.isManagerOrOwnerOrMechanic(request.getWorkshopId());
        workShopService.existWorkshop(request.getWorkshopId());
        userVehicleService.existByUserVehiclePublicId(request.getUserVehicleId());
        request.setStatus(EWorkOrderStatus.PENDING);
        WorkOrderEntity workOrder = workOrderMapper.toEntity(request);
        workOrder = workOrderRepository.save(workOrder);

        double totalPartsCost = 0.0;

        if (request.getWorkOrderitems() != null && !request.getWorkOrderitems().isEmpty()) {

            for (WorkOrderItemCreateDTO itemDTO : request.getWorkOrderitems()) {

                SparePartResponseDTO sparePart = sparePartService.getById(itemDTO.getSparePartId());

                WorkshopStockResponseDTO stock = workshopStockService.findWorkshopStockByWorkShopAndSparePart(request.getWorkshopId(), itemDTO.getSparePartId());

                if (stock.getStockQuantity() < itemDTO.getQuantityRequested()) {
                    throw new IncoherentDataException("Not enough stock for spare part: " + sparePart.getName());
                }

                double unitPrice = stock.getUnitprice();

                double subtotal = unitPrice * itemDTO.getQuantityRequested();


                WorkOrderItemCreateDTO workOrderItemCreate = WorkOrderItemCreateDTO.builder()
                        .sparePartId(sparePart.getId())
                        .quantityRequested(itemDTO.getQuantityRequested()).build();

                workOrderItemService.create(workOrderItemCreate);

                stock.setStockQuantity(stock.getStockQuantity() - itemDTO.getQuantityRequested());

                WorkshopStockUpdateDTO stockUpdate = WorkshopStockUpdateDTO.builder()
                        .unitPrice(stock.getUnitprice())
                        .stockQuantity(stock.getStockQuantity())
                        .minStockAlert(stock.getMinStockAlert())
                        .build();

                workshopStockService.update(stock.getId(),stockUpdate);
                totalPartsCost += subtotal;
            }
        }
        workOrder.setTotalCost(totalPartsCost + request.getLaborCharge());
        workOrderRepository.save(workOrder);


        return workOrderMapper.toResponse(workOrder);
    }

    public WorkOrderResponseDTO completeWorkOrder(Long workOrderId, CompleteWorkOrderDTO request){
        workshopPermissionService.isManagerOrOwnerOrMechanic(workOrderId);
        WorkOrderEntity workOrder = workOrderRepository.findById(workOrderId).
                orElseThrow(()->new EntityNotFoundException("Work Order Not Found"));

        MaintenanceCreateDTO maintenanceCreateDTO =  MaintenanceCreateDTO.builder().
                workOrderId(workOrder.getId()).
                maintenanceType(request.getMaintenanceType()).
                description(request.getDescription()).
                serviceKm(request.getServiceKm()).
                nextServiceKm(request.getNextServiceKm()).
                nextServiceDate(request.getNextServiceDate()).
                build();
        maintenanceService.create(workOrder.getUserVehicle().getPublicId(),maintenanceCreateDTO);
        return workOrderMapper.toResponse(workOrder);
    }

    @Override
    public List<WorkOrderResponseDTO> getAll() {
        return workOrderRepository.findAllByOrderByCreatedAtDesc().stream().
                map(workOrderMapper::toResponse).
                toList();
    }

    public List<WorkOrderResponseDTO> getAllByWorkshop(Long workshopId) {
        workshopPermissionService.isManagerOrOwnerOrMechanic(workshopId);
        return workOrderRepository.findByWorkshopIdOrderByCreatedAtDesc(workshopId).stream().
                map(workOrderMapper::toResponse)
                .sorted()
                .toList();
    }

    @Override
    public WorkOrderResponseDTO getById(Long id) {
        return workOrderRepository.findById(id).
                map(workOrderMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
    }

    public WorkOrderResponseDTO getByIdforWorkshop(Long id, Long workshopId) {
        workshopPermissionService.isManagerOrOwnerOrMechanic(workshopId);
        return workOrderRepository.findByIdAndWorkshopId(id,workshopId).
                map(workOrderMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
    }

    @Override
    public WorkOrderResponseDTO update(Long id, WorkOrderUpdateDTO request) {
        WorkOrderEntity workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
        workshopPermissionService.isManagerOrOwnerOrMechanic(workOrder.getWorkshop().getId());
        workOrderMapper.toEntityUpdate(request,workOrder);
        return workOrderMapper.toResponse(workOrderRepository.save(workOrder));
    }

    @Override
    public WorkOrderResponseDTO delete(Long id) {
        
        WorkOrderEntity workOrder = workOrderRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
        workshopPermissionService.isManagerOrOwnerOrMechanic(workOrder.getWorkshop().getId());
        WorkOrderResponseDTO response =  workOrderMapper.toResponse(workOrder);
        workOrderRepository.delete(workOrder);

        return response;
    }
}
