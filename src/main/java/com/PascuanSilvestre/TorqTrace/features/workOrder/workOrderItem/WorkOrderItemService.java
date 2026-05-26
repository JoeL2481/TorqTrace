package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.mapper.WorkOrderItemMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkOrderItemService implements ICrudService<WorkOrderItemCreateDTO, WorkOrderItemResponseDTO,Long> {

    private final WorkOrderItemRepository workOrderItemRepository;
    private final WorkOrderItemMapper workOrderItemMapper;

    @Override
    public WorkOrderItemResponseDTO create(WorkOrderItemCreateDTO request) {
        WorkOrderItemEntity workOrderItem = workOrderItemMapper.toEntity(request);
        workOrderItemRepository.save(workOrderItem);
        return workOrderItemMapper.toResponse(workOrderItem);
    }

    @Override
    public List<WorkOrderItemResponseDTO> getAll() {
        return workOrderItemRepository.findAll().stream().
                map(workOrderItemMapper::toResponse).
                toList();
    }

    @Override
    public WorkOrderItemResponseDTO getById(Long id) {
        return workOrderItemRepository.findById(id).
                map(workOrderItemMapper::toResponse).
                orElseThrow(()->new EntityNotFoundException("Work order item not found"));
    }

    @Override
    public WorkOrderItemResponseDTO update(Long id, WorkOrderItemCreateDTO request) {
        return null;
    }

    @Override
    public WorkOrderItemResponseDTO delete(Long id) {
        WorkOrderItemEntity workOrder = workOrderItemRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("Work order item not found"));
        WorkOrderItemResponseDTO response = workOrderItemMapper.toResponse(workOrder);
        workOrderItemRepository.deleteById(id);
        return response;
    }
}
