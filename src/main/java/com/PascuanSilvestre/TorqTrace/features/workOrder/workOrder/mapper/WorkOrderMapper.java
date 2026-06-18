package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;

import org.springframework.stereotype.Component;

@Component
public class WorkOrderMapper implements IWorkOrderMapper<WorkOrderEntity, WorkOrderCreateDTO, WorkOrderUpdateDTO, WorkOrderResponseDTO> {

    @Override
    public WorkOrderEntity toEntity(WorkOrderCreateDTO request) {
        WorkOrderEntity entity = new WorkOrderEntity();
        entity.setEntryKm(request.getEntryKm());
        entity.setDescription(request.getDescription());
        entity.setStatus(request.getStatus());
        entity.setWorkshopOrderType(request.getWorkOrderType());
        entity.setCurrency(request.getCurrency().name());
        entity.setLaborCharge(request.getLaborCharge());
        return entity;
    }

    @Override
    public WorkOrderResponseDTO toResponse(WorkOrderEntity entity) {
        WorkOrderResponseDTO response = new WorkOrderResponseDTO();
        response.setId(entity.getId());
        response.setEntryKm(entity.getEntryKm());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus());
        response.setWorkOrderType(entity.getWorkshopOrderType());
        response.setCurrency(entity.getCurrency());
        response.setLaborCharge(entity.getLaborCharge());
        response.setTotalCost(entity.getTotalCost());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
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
