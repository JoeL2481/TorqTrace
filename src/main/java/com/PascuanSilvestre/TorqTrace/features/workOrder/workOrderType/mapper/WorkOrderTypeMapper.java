package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.WorkOrderTypeEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeResponseDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderTypeMapper implements IMapper<WorkOrderTypeEntity, WorkOrderTypeRequestDTO, WorkOrderTypeResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkOrderTypeEntity toEntity(WorkOrderTypeRequestDTO request) {
        return  modelMapper.map(request, WorkOrderTypeEntity.class);
    }

    @Override
    public WorkOrderTypeResponseDTO toResponse(WorkOrderTypeEntity entity) {
        return  modelMapper.map(entity, WorkOrderTypeResponseDTO.class);
    }
}
