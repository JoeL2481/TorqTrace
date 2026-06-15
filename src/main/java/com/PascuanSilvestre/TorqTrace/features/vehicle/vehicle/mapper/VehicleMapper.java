package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.ShortPublicIdGenerator;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateCompleteDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleEntity toEntity(VehicleCreateDTO dto) {
        VehicleEntity entity = new VehicleEntity();

        entity.setPublicId(ShortPublicIdGenerator.generate());
        entity.setVehicleBodyType(dto.getVehicleBodyType());
        entity.setVehicleCategory(dto.getVehicleCategory());

        return entity;
    }

    public VehicleEntity toEntity(VehicleCreateCompleteDTO dto) {
        VehicleEntity entity = new VehicleEntity();

        entity.setPublicId(ShortPublicIdGenerator.generate());
        entity.setVehicleBodyType(dto.getVehicleBodyType());
        entity.setVehicleCategory(dto.getVehicleCategory());

        return entity;
    }

    public VehicleResponseDTO toResponse(VehicleEntity entity) {
        VehicleResponseDTO dto = new VehicleResponseDTO();

        dto.setPublicId(entity.getPublicId());
        dto.setVehicleBodyType(entity.getVehicleBodyType());
        dto.setVehicleCategory(entity.getVehicleCategory());

        if (entity.getVehicleBrand() != null) {
            dto.setVehicleBrandId(entity.getVehicleBrand().getPublicId());
        }

        if (entity.getVehicleModel() != null) {
            dto.setVehicleModelId(entity.getVehicleModel().getId());
        }

        if (entity.getVehicleGeneration() != null) {
            dto.setVehicleGenerationId(entity.getVehicleGeneration().getId());
        }

        if (entity.getVehicleVariant() != null) {
            dto.setVehicleVariantId(entity.getVehicleVariant().getId());
        }

        if (entity.getVehicleEquipmentLevel() != null) {
            dto.setVehicleEquipmentLevelId(entity.getVehicleEquipmentLevel().getId());
        }

        if (entity.getEngine() != null) {
            dto.setEngineId(entity.getEngine().getId());
        }

        if (entity.getTransmission() != null) {
            dto.setTransmissionId(entity.getTransmission().getId());
        }

        return dto;
    }

    public VehicleDetailedResponseDTO toDetailedResponse(VehicleEntity entity) {
        VehicleDetailedResponseDTO dto = new VehicleDetailedResponseDTO();

        dto.setPublicId(entity.getPublicId());
        dto.setVehicleBodyType(entity.getVehicleBodyType());
        dto.setVehicleCategory(entity.getVehicleCategory());

        if (entity.getVehicleBrand() != null) {
            dto.setVehicleBrandName(entity.getVehicleBrand().getName());
        }

        if (entity.getVehicleModel() != null) {
            dto.setVehicleModelName(entity.getVehicleModel().getName());
        }

        if (entity.getVehicleGeneration() != null) {
            dto.setVehicleGenerationName(entity.getVehicleGeneration().getName());
            dto.setVehicleGenerationAlias(entity.getVehicleGeneration().getAlias());
        }

        if (entity.getVehicleVariant() != null) {
            dto.setVehicleVariantName(entity.getVehicleVariant().getName());
        }

        if (entity.getVehicleEquipmentLevel() != null) {
            dto.setVehicleEquipmentLevelName(entity.getVehicleEquipmentLevel().getName());
        }

        if (entity.getEngine() != null) {
            dto.setEngineCode(entity.getEngine().getCode());
            dto.setEngineName(entity.getEngine().getName());
        }

        if (entity.getTransmission() != null) {
            dto.setTransmissionName(entity.getTransmission().getName());
        }

        return dto;
    }
}
