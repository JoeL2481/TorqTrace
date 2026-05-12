package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;


import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderMapper implements IMapper<WorkOrderEntity, WorkOrderCreateDTO, WorkOrderResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public WorkOrderEntity toEntity(WorkOrderCreateDTO request) {
        return modelMapper.map(request, WorkOrderEntity.class);
    }

    @Override
    public WorkOrderResponseDTO toResponse(WorkOrderEntity entity) {
        return modelMapper.map(entity, WorkOrderResponseDTO.class);
    }
}
