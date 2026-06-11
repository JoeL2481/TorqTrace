package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.mapper;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleEntity toEntity(VehicleCreateDTO)

    public VehicleResponseDTO toResponse(VehicleEntity entity) {
        return VehicleResponseDTO.builder()
                .publicId(entity.getPublicId())
                .vehicleBrand(entity.getVehicleBrand() == null ? null : VehicleBrandResponseDTO.builder()
                        .id(entity.getVehicleBrand().getPublicId())
                        .name(entity.getVehicleBrand().getName())
                        .build())
                .vehicleModel(entity.getVehicleModel() == null ? null : VehicleModelResponseDTO.builder()
                        .id(entity.getVehicleModel().getId())
                        .name(entity.getVehicleModel().getName())
                        .build())
                .vehicleVariant(entity.getVehicleVariant() == null ? null : VehicleVariantResponseDTO.builder()
                        .id(entity.getVehicleVariant().getId())
                        .name(entity.getVehicleVariant().getName())
                        .build())
                .vehicleGeneration(entity.getVehicleGeneration() == null ? null : VehicleGenerationResponseDTO.builder()
                        .id(entity.getVehicleGeneration().getId())
                        .name(entity.getVehicleGeneration().getName())
                        .alias(entity.getVehicleGeneration().getAlias())
                        .yearsFrom(entity.getVehicleGeneration().getYearFrom())
                        .monthFrom(entity.getVehicleGeneration().getMonthFrom())
                        .yearsTo(entity.getVehicleGeneration().getYearTo())
                        .monthTo(entity.getVehicleGeneration().getMonthTo())
                        .build())
                .vehicleEquipmentLevel(entity.getVehicleEquipmentLevel() == null ? null : VehicleEquipmentLevelResponseDTO.builder()
                        .id(entity.getVehicleEquipmentLevel().getId())
                        .name(entity.getVehicleEquipmentLevel().getName())
                        .build())
                .vehicleCategory(entity.getVehicleCategory())
                .build();
    }
}
