package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;

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

        if (entity.getWorkshop() != null) {
            response.setWorkshop(WorkShopResponseDTO.builder()
                    .id(entity.getWorkshop().getId())
                    .name(entity.getWorkshop().getName())
                    .description(entity.getWorkshop().getDescription())
                    .workshopAddress(entity.getWorkshop().getWorkshopAddress())
                    .workshopContactInfo(entity.getWorkshop().getWorkshopContactInfo())
                    .status(entity.getWorkshop().isStatus())
                    .createdAt(entity.getWorkshop().getCreatedAt())
                    .build());
        }

        if (entity.getClient() != null) {
            response.setClient(WorkshopClientResponseDTO.builder()
                    .id(entity.getClient().getId())
                    .description(entity.getClient().getDescription())
                    .createdAt(entity.getClient().getCreatedAt())
                    .updatedAt(entity.getClient().getUpdatedAt())
                    .user(entity.getClient().getUser() == null ? null : UserResponseDTO.builder()
                            .publicId(entity.getClient().getUser().getPublicId())
                            .firstName(entity.getClient().getUser().getFirstName())
                            .lastName(entity.getClient().getUser().getLastName())
                            .userAddress(entity.getClient().getUser().getUserAddress())
                            .userContactInfo(entity.getClient().getUser().getUserContactInfo())
                            .userStatus(entity.getClient().getUser().getStatus())
                            .build())
                    .build());
        }

        if (entity.getUserVehicle() != null) {
            UserVehicleResponseDTO userVehicle = new UserVehicleResponseDTO();
            userVehicle.setPublicId(entity.getUserVehicle().getPublicId());
            userVehicle.setLicencePlate(entity.getUserVehicle().getLicencePlate());
            userVehicle.setYear(entity.getUserVehicle().getYear());
            userVehicle.setCurrentKm(entity.getUserVehicle().getCurrentKm());
            userVehicle.setVin(entity.getUserVehicle().getVin());

            if (entity.getUserVehicle().getUser() != null) {
                userVehicle.setUserId(entity.getUserVehicle().getUser().getPublicId());
                if (entity.getUserVehicle().getUser().getUserContactInfo() != null) {
                    userVehicle.setUserEmail(entity.getUserVehicle().getUser().getUserContactInfo().getEmail());
                }
            }

            if (entity.getUserVehicle().getParticularVehicle() != null) {
                userVehicle.setParticularVehicleId(entity.getUserVehicle().getParticularVehicle().getPublicId());
                userVehicle.setVehicleBrandName(entity.getUserVehicle().getParticularVehicle().getVehicleBrand().getName());
                userVehicle.setVehicleModelName(entity.getUserVehicle().getParticularVehicle().getVehicleModel().getName());

                if (entity.getUserVehicle().getParticularVehicle().getVehicleVariant() != null) {
                    userVehicle.setVehicleVariantName(entity.getUserVehicle().getParticularVehicle().getVehicleVariant().getName());
                }
                if (entity.getUserVehicle().getParticularVehicle().getVehicleGeneration() != null) {
                    userVehicle.setVehicleGenerationName(entity.getUserVehicle().getParticularVehicle().getVehicleGeneration().getName());
                }
                if (entity.getUserVehicle().getParticularVehicle().getVehicleEquipmentLevel() != null) {
                    userVehicle.setVehicleEquipmentLevelName(entity.getUserVehicle().getParticularVehicle().getVehicleEquipmentLevel().getName());
                }
                if (entity.getUserVehicle().getParticularVehicle().getEngine() != null) {
                    userVehicle.setEngineCode(entity.getUserVehicle().getParticularVehicle().getEngine().getCode());
                }
                if (entity.getUserVehicle().getParticularVehicle().getTransmission() != null) {
                    userVehicle.setTransmissionName(entity.getUserVehicle().getParticularVehicle().getTransmission().getName());
                }
            }

            response.setUserVehicle(userVehicle);
        }

        if (entity.getWorkOrderItems() != null && !entity.getWorkOrderItems().isEmpty()) {
            WorkOrderItemEntity item = entity.getWorkOrderItems().get(0);
            response.setWorkOrderItem(WorkOrderItemResponseDTO.builder()
                    .id(item.getId())
                    .quantityRequested(item.getQuantityRequested())
                    .unitPriceAtTime(item.getUnitPrice())
                    .subtotal(item.getSubtotal())
                    .createdAt(item.getCreatedAt())
                    .updatedAt(item.getUpdatedAt())
                    .sparePart(item.getSparePart() == null ? null : SparePartResponseDTO.builder()
                            .id(item.getSparePart().getId())
                            .name(item.getSparePart().getName())
                            .description(item.getSparePart().getDescription())
                            .build())
                    .build());
        }

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
