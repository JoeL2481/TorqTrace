package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.mapper;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class UserVehicleMapper {

    public UserVehicleEntity toEntity(UserVehicleCreateDTO request) {
        UserVehicleEntity entity = new UserVehicleEntity();
        entity.setLicencePlate(request.getLicencePlate());
        entity.setYear(request.getYear());
        entity.setCurrentKm(request.getCurrentKm());
        entity.setVin(request.getVin());
        return entity;
    }

    public void toEntityUpdate(UserVehicleUpdateDTO request, UserVehicleEntity entity) {
        if (request.getLicencePlate() != null && !request.getLicencePlate().isBlank()) {
            entity.setLicencePlate(request.getLicencePlate());
        }

        if (request.getYear() != null) {
            entity.setYear(request.getYear());
        }

        if (request.getCurrentKm() != null) {
            entity.setCurrentKm(request.getCurrentKm());
        }

        if (request.getVin() != null && !request.getVin().isBlank()) {
            entity.setVin(request.getVin());
        }
    }

    public UserVehicleResponseDTO toResponse(UserVehicleEntity entity) {
        UserVehicleResponseDTO response = new UserVehicleResponseDTO();
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        response.setUserPublicId(entity.getUser().getPublicId());
        response.setLicencePlate(entity.getLicencePlate());
        response.setYear(entity.getYear());
        response.setCurrentKm(entity.getCurrentKm());
        response.setVin(entity.getVin());

        if (entity.getParticularVehicle() != null) {
            response.setParticularVehicleId(entity.getParticularVehicle().getPublicId());
            response.setVehicleBrandName(entity.getParticularVehicle().getVehicleBrand().getName());
            response.setVehicleModelName(entity.getParticularVehicle().getVehicleModel().getName());

            if (entity.getParticularVehicle().getVehicleVariant() != null) {
                response.setVehicleVariantName(entity.getParticularVehicle().getVehicleVariant().getName());
            }

            if (entity.getParticularVehicle().getVehicleGeneration() != null) {
                response.setVehicleGenerationName(entity.getParticularVehicle().getVehicleGeneration().getName());
            }

            if (entity.getParticularVehicle().getVehicleEquipmentLevel() != null) {
                response.setVehicleEquipmentLevelName(entity.getParticularVehicle().getVehicleEquipmentLevel().getName());
            }

            if (entity.getParticularVehicle().getEngine() != null) {
                response.setEngineCode(entity.getParticularVehicle().getEngine().getCode());
            }

            if (entity.getParticularVehicle().getTransmission() != null) {
                response.setTransmissionName(entity.getParticularVehicle().getTransmission().getName());
            }
        }

        return response;
    }
}
