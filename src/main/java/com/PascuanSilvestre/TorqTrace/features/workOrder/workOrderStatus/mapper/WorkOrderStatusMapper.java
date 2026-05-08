package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderStatus.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderStatus.WorkOrderStatusEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderStatus.dto.WorkOrderStatusRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderStatus.dto.WorkOrderStatusResponseDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderStatusMapper implements IMapper<WorkOrderStatusEntity,WorkOrderStatusRequestDTO,WorkOrderStatusResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public WorkOrderStatusEntity toEntity(WorkOrderStatusRequestDTO request) {
        return  modelMapper.map(request,WorkOrderStatusEntity.class);
    }

    @Override
    public WorkOrderStatusResponseDTO toResponse(WorkOrderStatusEntity entity) {
        return  modelMapper.map(entity,WorkOrderStatusResponseDTO.class);
    }
}
