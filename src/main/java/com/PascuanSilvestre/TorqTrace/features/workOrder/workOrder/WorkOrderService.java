package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.common.exception.IncoherentDataException;
import com.PascuanSilvestre.TorqTrace.common.exception.ProhibitedOperationException;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartService;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.MaintenanceService;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper.WorkOrderMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemRepository;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.WorkshopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.WorkshopClientRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.WorkshopStockService;
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
    private final WorkShopRepository workShopRepository;
    private final WorkshopClientRepository workshopClientRepository;
    private final UserVehicleService userVehicleService;
    private final SparePartService sparePartService;
    private final WorkshopStockService workshopStockService;
    private final WorkOrderItemRepository workOrderItemRepository;
    private final MaintenanceService maintenanceService;

    @Transactional
    @Override
    public WorkOrderResponseDTO create(WorkOrderCreateDTO request) {

        if(!workshopPermissionService.isManagerOrOwnerOrMechanic(request.getWorkshopId())){
            throw new ProhibitedOperationException("Workshop does'nt exist or only the workshop owner or manager can perform this action");
        }
        workShopService.existWorkshop(request.getWorkshopId());
        userVehicleService.existByUserVehiclePublicId(request.getUserVehicleId());

        WorkShopEntity workshop = workShopRepository.findById(request.getWorkshopId())
                .orElseThrow(() -> new EntityNotFoundException("Workshop not found"));
        UserVehicleEntity userVehicle = userVehicleService.getAnyVehicleById(request.getUserVehicleId());
        WorkshopClientEntity client = getOrCreateWorkshopClient(workshop, userVehicle);

        request.setStatus(EWorkOrderStatus.PENDING);
        WorkOrderEntity workOrder = workOrderMapper.toEntity(request);
        workOrder.setWorkshop(workshop);
        workOrder.setUserVehicle(userVehicle);
        workOrder.setClient(client);
        workOrder = workOrderRepository.save(workOrder);

        double totalPartsCost = 0.0;

        if (request.getWorkOrderitems() != null && !request.getWorkOrderitems().isEmpty()) {

            for (WorkOrderItemCreateDTO itemDTO : request.getWorkOrderitems()) {

                SparePartEntity sparePart = sparePartService.getEntityById(itemDTO.getSparePartId());

                WorkshopStockResponseDTO stock = workshopStockService.findWorkshopStockByWorkShopAndSparePart(request.getWorkshopId(), itemDTO.getSparePartId());

                if (stock.getStockQuantity() < itemDTO.getQuantityRequested()) {
                    throw new IncoherentDataException("Not enough stock for spare part: " + sparePart.getName());
                }

                double unitPrice = stock.getUnitprice();

                double subtotal = unitPrice * itemDTO.getQuantityRequested();


                WorkOrderItemEntity workOrderItem = WorkOrderItemEntity.builder()
                        .workOrder(workOrder)
                        .sparePart(sparePart)
                        .quantityRequested(itemDTO.getQuantityRequested())
                        .unitPrice(unitPrice)
                        .subtotal(subtotal)
                        .build();
                workOrderItemRepository.save(workOrderItem);

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

    private WorkshopClientEntity getOrCreateWorkshopClient(WorkShopEntity workshop, UserVehicleEntity userVehicle) {
        return workshopClientRepository.findByUserIdAndWorkshopId(userVehicle.getUser().getId(), workshop.getId())
                .orElseGet(() -> workshopClientRepository.save(WorkshopClientEntity.builder()
                        .workshop(workshop)
                        .user(userVehicle.getUser())
                        .description("Created automatically from work order")
                        .build()));
    }

    @Override
    public List<WorkOrderResponseDTO> getAll() {
        return workOrderRepository.findAllByOrderByCreatedAtDesc().stream().
                map(workOrderMapper::toResponse).
                toList();
    }

    public List<WorkOrderResponseDTO> getAllByWorkshop(Long workshopId) {
        if(!workshopPermissionService.isManagerOrOwnerOrMechanic(workshopId)){
            throw new ProhibitedOperationException("Workshop does'nt exist or only the workshop owner or manager can perform this action");
        }
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
        if(!workshopPermissionService.isManagerOrOwnerOrMechanic(workshopId)){
            throw new ProhibitedOperationException("Workshop does'nt exist or only the workshop owner or manager can perform this action");
        }
        return workOrderRepository.findByIdAndWorkshopId(id,workshopId).
                map(workOrderMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
    }

    @Override
    @Transactional
    public WorkOrderResponseDTO update(Long id, WorkOrderUpdateDTO request) {

        WorkOrderEntity workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkOrder was not found"));

        if (!workshopPermissionService.isManagerOrOwnerOrMechanic(workOrder.getWorkshop().getId())) {
            throw new ProhibitedOperationException(
                    "Workshop doesn't exist or only the workshop owner, manager or mechanic can perform this action");
        }
        EWorkOrderStatus previousStatus = workOrder.getStatus();
        workOrderMapper.toEntityUpdate(request, workOrder);

        if (previousStatus != EWorkOrderStatus.COMPLETED && workOrder.getStatus() == EWorkOrderStatus.COMPLETED && workOrder.getMaitenance() == null) {

            MaintenanceCreateDTO newMaitenance =  MaintenanceCreateDTO.builder()
                    .workOrderId(workOrder.getId())
                    .serviceKm(workOrder.getEntryKm())
                    .nextServiceKm(request.getNextServiceKm())
                    .EMaintenanceType(request.getMaintenanceType())
                    .description(workOrder.getDescription())
                    .nextServiceDate(request.getNextServiceDate())
                    .build();

            maintenanceService.create(workOrder.getUserVehicle().getPublicId(),newMaitenance);

        }

        return workOrderMapper.toResponse(
                workOrderRepository.save(workOrder)
        );
    }

    @Override
    public WorkOrderResponseDTO delete(Long id) {
        
        WorkOrderEntity workOrder = workOrderRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
        if(!workshopPermissionService.isManagerOrOwnerOrMechanic(workOrder.getWorkshop().getId())){
            throw new ProhibitedOperationException("Workshop does'nt exist or only the workshop owner or manager can perform this action");
        }
        WorkOrderResponseDTO response =  workOrderMapper.toResponse(workOrder);
        workOrderRepository.delete(workOrder);

        return response;
    }
}
