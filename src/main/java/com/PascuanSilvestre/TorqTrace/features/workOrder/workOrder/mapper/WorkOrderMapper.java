package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderMapper implements IWorkOrderMapper<WorkOrderEntity, WorkOrderCreateDTO, WorkOrderUpdateDTO, WorkOrderResponseDTO> {

    private final ModelMapper mapper;

    @Override
    public WorkOrderEntity toEntity(WorkOrderCreateDTO request) {
        return mapper.map(request, WorkOrderEntity.class);
    }

    @Override
    public WorkOrderResponseDTO toResponse(WorkOrderEntity entity) {
        return mapper.map(entity, WorkOrderResponseDTO.class);
    }

    public void toEntityUpdate(WorkOrderUpdateDTO request, WorkOrderEntity entity) {

        if (request.getEntryKm() != null) {
            entity.setEntryKm(request.getEntryKm());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

        if (request.getWorkshopOrderType() != null) {
            entity.setWorkshopOrderType(request.getWorkshopOrderType());
        }

        if (request.getCurrency() != null) {
            entity.setCurrency(request.getCurrency());
        }

        if (request.getLaborCharge() != null) {
            entity.setLaborCharge(request.getLaborCharge());
        }

        if (request.getTotalCost() != null) {
            entity.setTotalCost(request.getTotalCost());
        }
    }
}
