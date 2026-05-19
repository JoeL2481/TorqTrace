package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.mapper.WorkShopStockMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkShopStockService implements ICrudServiceComplete<WorkShopStockCreateDTO, WorkShopStockUpdateDTO, WorkShopStockResponseDTO,Long> {
    private final WorkShopStockRepository workShopStockRepository;
    private final WorkShopStockMapper workShopStockMapper;
    @Override
    public WorkShopStockResponseDTO create(WorkShopStockCreateDTO request) {
        WorkShopStockEntity workShopStock = workShopStockMapper.toEntity(request);
        workShopStockRepository.save(workShopStock);
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public List<WorkShopStockResponseDTO> getAll() {
        return workShopStockRepository.findAll().stream().
                map(workShopStockMapper::toResponse)
                .toList();
    }

    @Override
    public WorkShopStockResponseDTO getById(Long id) {
        WorkShopStockEntity workShopStock = workShopStockRepository.
                findById(id).
                orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkShopStockResponseDTO update(Long id, WorkShopStockUpdateDTO request) {
        WorkShopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        workShopStockMapper.toEntityUpdate(request,workShopStock);
        workShopStockRepository.save(workShopStock);
        return workShopStockMapper.toResponse(workShopStock);
    }

    @Override
    public WorkShopStockResponseDTO delete(Long id) {
        WorkShopStockEntity workShopStock = workShopStockRepository.findById(id).orElseThrow(()->new EntityNotFoundException("WorkShopStock with id " + id + " not found"));
        WorkShopStockResponseDTO response = workShopStockMapper.toResponse(workShopStock);
        workShopStockRepository.delete(workShopStock);
        return response;
    }
}
