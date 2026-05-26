package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.mapper.WorkOrderTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkOrderTypeService implements ICrudService<WorkOrderTypeCreateDTO, WorkOrderTypeResponseDTO,Long> {

    private final WorkOrderTypeRepository workOrderTypeRepository;
    private final WorkOrderTypeMapper workOrderTypeMapper;
    @Override
    public WorkOrderTypeResponseDTO create(WorkOrderTypeCreateDTO request) {
        WorkOrderTypeEntity workORderType = workOrderTypeMapper.toEntity(request);
        workOrderTypeRepository.save(workORderType);
        return workOrderTypeMapper.toResponse(workORderType);
    }

    @Override
    public List<WorkOrderTypeResponseDTO> getAll() {
        return workOrderTypeRepository.findAll().stream().map(workOrderTypeMapper::toResponse).toList();
    }

    @Override
    public WorkOrderTypeResponseDTO getById(Long id) {
        return workOrderTypeRepository.findById(id).
                map(workOrderTypeMapper::toResponse).
                orElseThrow(()->new EntityNotFoundException("work order type not found"));
    }

    @Override
    public WorkOrderTypeResponseDTO update(Long aLong, WorkOrderTypeCreateDTO request) {
        return null;
    }

    @Override
    public WorkOrderTypeResponseDTO delete(Long id) {
        WorkOrderTypeEntity workOrderType = workOrderTypeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Work order item not found"));
        WorkOrderTypeResponseDTO response = workOrderTypeMapper.toResponse(workOrderType);
        workOrderTypeRepository.delete(workOrderType);
        return response;
    }
}
