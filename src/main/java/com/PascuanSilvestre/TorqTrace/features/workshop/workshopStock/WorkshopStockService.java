package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.ProhibitedOperationException;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.mapper.WorkshopStockMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkshopStockService implements IWorkshopStockService<WorkshopStockCreateDTO, WorkshopStockUpdateDTO, WorkshopStockResponseDTO,Long> {
    private final WorkshopStockRepository workShopStockRepository;
    private final WorkshopStockMapper workShopStockMapper;
    private final WorkshopPermissionService permissionService;
    private final SparePartService sparePartService;
    private final WorkShopRepository workShopRepository;

    @Override
    public WorkshopStockResponseDTO create(WorkshopStockCreateDTO request) {


        if (!permissionService.isManagerOrOwnerOrMechanic(request.getWorkshopId())) {
            throw new ProhibitedOperationException("Not permissions enough");
        }

        if (workShopStockRepository.findByWorkshopIdAndSparePartId(request.getWorkshopId(), request.getSparePartId()).isPresent()) {
            throw new AlreadyExistsException("This spare part already exists in the workshop stock");
        }

        WorkShopEntity workshop = workShopRepository.findById(request.getWorkshopId())
                .orElseThrow(() -> new EntityNotFoundException("Workshop not found"));
        SparePartEntity sparePart = sparePartService.getEntityById(request.getSparePartId());

        WorkshopStockEntity workShopStock = workShopStockMapper.toEntity(request);
        workShopStock.setWorkshop(workshop);
        workShopStock.setSparePart(sparePart);

        workShopStock = workShopStockRepository.save(workShopStock);

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public List<WorkshopStockResponseDTO> getAll(Long workShopId) {
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopId)) {
            throw new ProhibitedOperationException("Not permissions enough");
        }
        return workShopStockRepository.findByWorkshopId(workShopId).stream().
                map(workShopStockMapper::toResponse)
                .toList();
    }

    @Override
    public WorkshopStockResponseDTO getById(Long id) {

        WorkshopStockEntity workShopStock = workShopStockRepository.
                findById(id).
                orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));

        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new ProhibitedOperationException("Not permissions enough");
        }

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkshopStockResponseDTO update(Long id, WorkshopStockUpdateDTO request) {
        WorkshopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new ProhibitedOperationException("Not permissions enough");
        }
        workShopStockMapper.toEntityUpdate(request,workShopStock);
        workShopStockRepository.save(workShopStock);
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkshopStockResponseDTO delete(Long id) {
        WorkshopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new ProhibitedOperationException("Not permissions enough");
        }
        WorkshopStockResponseDTO response = workShopStockMapper.toResponse(workShopStock);
        workShopStockRepository.delete(workShopStock);
        return response;
    }

    public WorkshopStockResponseDTO findWorkshopStockByWorkShopAndSparePart(Long workshopId, Long sparePartId) {
    return workShopStockRepository.findByWorkshopIdAndSparePartId(workshopId,sparePartId).map(workShopStockMapper::toResponse).
                orElseThrow(()->new EntityNotFoundException("WorkshopStock with id " + workshopId + " sparePart not found"));
    }



    public boolean existWorkshopStock(Long id) {
        if (!workShopStockRepository.existsById(id)){
            throw new EntityNotFoundException("WorkshopStock not found");
        }
        return true;
    }
}
