package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.mapper.WorkShopStockMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkShopStockService implements IWorkShopStockService<WorkShopStockCreateDTO, WorkShopStockUpdateDTO, WorkShopStockResponseDTO,Long> {
    private final WorkShopStockRepository workShopStockRepository;
    private final WorkShopStockMapper workShopStockMapper;
    private final WorkshopPermissionService permissionService;
    private final SparePartService sparePartService;

    @Override
    public WorkShopStockResponseDTO create(WorkShopStockCreateDTO request) {


        if (!permissionService.isManagerOrOwnerOrMechanic(request.getWorkshopId())) {
            throw new AccessDeniedException("Not permissions enough");
        }

        sparePartService.existSparePart(request.getSparePartId());

        WorkShopStockEntity workShopStock = workShopStockMapper.toEntity(request);

        workShopStockRepository.save(workShopStock);

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public List<WorkShopStockResponseDTO> getAll(Long workShopId) {
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopId)) {
            throw new AccessDeniedException("Not permissions enough");
        }
        return workShopStockRepository.findByWorkshopId(workShopId).stream().
                map(workShopStockMapper::toResponse)
                .toList();
    }

    @Override
    public WorkShopStockResponseDTO getById(Long id) {

        WorkShopStockEntity workShopStock = workShopStockRepository.
                findById(id).
                orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));

        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new AccessDeniedException("Not permissions enough");
        }

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkShopStockResponseDTO update(Long id, WorkShopStockUpdateDTO request) {
        WorkShopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new AccessDeniedException("Not permissions enough");
        }
        workShopStockMapper.toEntityUpdate(request,workShopStock);
        workShopStockRepository.save(workShopStock);
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkShopStockResponseDTO delete(Long id) {
        WorkShopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new AccessDeniedException("Not permissions enough");
        }
        WorkShopStockResponseDTO response = workShopStockMapper.toResponse(workShopStock);
        workShopStockRepository.delete(workShopStock);
        return response;
    }

    public boolean existWorkshopStock(Long id) {
        if (!workShopStockRepository.existsById(id)){
            throw new EntityNotFoundException("WorkshopStock not found");
        }
        return true;
    }
}
