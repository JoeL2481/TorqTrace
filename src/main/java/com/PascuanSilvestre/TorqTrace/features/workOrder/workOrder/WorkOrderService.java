package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper.WorkOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkOrderService implements ICrudServiceComplete<WorkOrderCreateDTO, WorkOrderUpdateDTO, WorkOrderResponseDTO,Long> {

    private final WorkOrderMapper workOrderMapper;
    private final WorkOrderRepository workOrderRepository;


    @Override
    public WorkOrderResponseDTO create(WorkOrderCreateDTO request) {
        WorkOrderEntity workOrderEntity = workOrderMapper.toEntity(request);
        workOrderRepository.save(workOrderEntity);
        return workOrderMapper.toResponse(workOrderEntity);
    }

    @Override
    public List<WorkOrderResponseDTO> getAll() {
        return workOrderRepository.findAll().stream().
                map(workOrderMapper::toResponse).
                toList();
    }

    @Override
    public WorkOrderResponseDTO getById(Long id) {
        return workOrderRepository.findById(id).
                map(workOrderMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
    }

    @Override
    public WorkOrderResponseDTO update(Long id, WorkOrderUpdateDTO request) {
            WorkOrderEntity workOrder = workOrderRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
        workOrderMapper.toEntityUpdate(request,workOrder);
        return workOrderMapper.toResponse(workOrderRepository.save(workOrder));
    }

    @Override
    public WorkOrderResponseDTO delete(Long id) {
        WorkOrderEntity workOrder = workOrderRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workOrder was not found"));
        WorkOrderResponseDTO response =  workOrderMapper.toResponse(workOrder);
        workOrderRepository.delete(workOrder);

        return response;
    }
}
