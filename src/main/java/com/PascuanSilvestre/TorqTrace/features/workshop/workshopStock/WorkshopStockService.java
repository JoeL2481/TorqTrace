package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.mapper.WorkshopStockMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkshopStockService implements IWorkshopStockService<WorkshopStockCreateDTO, WorkshopStockUpdateDTO, WorkshopStockResponseDTO,Long> {
    private final WorkshopStockRepository workShopStockRepository;
    private final WorkshopStockMapper workShopStockMapper;
    private final WorkshopPermissionService permissionService;
    private final SparePartService sparePartService;

    @Override
    public WorkshopStockResponseDTO create(WorkshopStockCreateDTO request) {


        if (!permissionService.isManagerOrOwnerOrMechanic(request.getWorkshopId())) {
            throw new AccessDeniedException("Not permissions enough");
        }

        sparePartService.existSparePart(request.getSparePartId());

        WorkshopStockEntity workShopStock = workShopStockMapper.toEntity(request);

        workShopStockRepository.save(workShopStock);

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public List<WorkshopStockResponseDTO> getAll(Long workShopId) {
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopId)) {
            throw new AccessDeniedException("Not permissions enough");
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
            throw new AccessDeniedException("Not permissions enough");
        }

        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkshopStockResponseDTO update(Long id, WorkshopStockUpdateDTO request) {
        WorkshopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new AccessDeniedException("Not permissions enough");
        }
        workShopStockMapper.toEntityUpdate(request,workShopStock);
        workShopStockRepository.save(workShopStock);
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkshopStockResponseDTO delete(Long id) {
        WorkshopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        if (!permissionService.isManagerOrOwnerOrMechanic(workShopStock.getWorkshop().getId())) {
            throw new AccessDeniedException("Not permissions enough");
        }
        WorkshopStockResponseDTO response = workShopStockMapper.toResponse(workShopStock);
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
