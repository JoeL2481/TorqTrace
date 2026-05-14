package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderItemMapper implements IMapper<WorkOrderItemEntity, WorkOrderItemCreateDTO, WorkOrderItemResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkOrderItemEntity toEntity(WorkOrderItemCreateDTO request) {
        return modelMapper.map(request,WorkOrderItemEntity.class);
    }

    @Override
    public WorkOrderItemResponseDTO toResponse(WorkOrderItemEntity entity) {
        return modelMapper.map(entity,WorkOrderItemResponseDTO.class);
    }
}
