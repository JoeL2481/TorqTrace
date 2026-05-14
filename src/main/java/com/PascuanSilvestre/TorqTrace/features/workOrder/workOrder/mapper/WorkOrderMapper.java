package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;


import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.WorkOrderTypeEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkOrderMapper implements IMapper<WorkOrderEntity, WorkOrderCreateDTO, WorkOrderResponseDTO> {

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

        if (request.getWorkshopId() != null) {
            entity.setWorkshop(
                    WorkShopEntity.builder()
                            .id(request.getWorkshopId())
                            .build()
            );
        }

        if (request.getClientId() != null) {
            entity.setClient(
                    WorkShopClientEntity.builder()
                            .id(request.getClientId())
                            .build()
            );
        }

        if (request.getVehicleId() != null) {
            entity.setVehicle(
                    VehicleEntity.builder()
                            .id(request.getVehicleId())
                            .build()
            );
        }

        if (request.getEntryKm() != null) {
            entity.setEntryKm(request.getEntryKm());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }

    /*
    if (request.getUserVehicleMaitenanceId() != null) {
        entity.setUserVehicleMaitenance(
                UserVehicleMaitenanceEntity.builder()
                        .id(request.getUserVehicleMaitenanceId())
                        .build()
        );
    }
    */

        if (request.getWorkshopOrderTypeId() != null) {
            entity.setWorkshopOrderType(
                    WorkOrderTypeEntity.builder()
                            .id(request.getWorkshopOrderTypeId())
                            .build()
            );
        }

        if (request.getWorkOrderItemId() != null) {
            entity.setWorkOrderItem(
                    WorkOrderItemEntity.builder()
                            .id(request.getWorkOrderItemId())
                            .build()
            );
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
