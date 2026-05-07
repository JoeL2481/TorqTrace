package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderItemMapper implements IMapper<WorkOrderItemEntity,WorkOrderItemRequestDTO, WorkOrderItemResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkOrderItemEntity toEntity(WorkOrderItemRequestDTO request) {
        return modelMapper.map(request,WorkOrderItemEntity.class);
    }

    @Override
    public WorkOrderItemResponseDTO toResponse(WorkOrderItemEntity entity) {
        return modelMapper.map(entity,WorkOrderItemResponseDTO.class);
    }
}
