package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.WorkOrderTypeEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderTypeMapper implements IMapper<WorkOrderTypeEntity, WorkOrderTypeCreateDTO, WorkOrderTypeResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkOrderTypeEntity toEntity(WorkOrderTypeCreateDTO request) {
        return  modelMapper.map(request, WorkOrderTypeEntity.class);
    }

    @Override
    public WorkOrderTypeResponseDTO toResponse(WorkOrderTypeEntity entity) {
        return  modelMapper.map(entity, WorkOrderTypeResponseDTO.class);
    }
}
