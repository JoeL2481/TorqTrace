package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleGenerationMapper implements IMapper<VehicleGenerationEntity, VehicleGenerationRequestDTO, VehicleGenerationResponseDTO> {
    @Override
    public VehicleGenerationEntity toEntity(VehicleGenerationRequestDTO dto) {
        return VehicleGenerationEntity.builder()
                .name(dto.getName())
                .alias(dto.getAlias())
                .yearFrom(dto.getYearsFrom())
                .monthFrom(dto.getMonthFrom())
                .yearTo(dto.getYearsTo())
                .monthTo(dto.getMonthTo())
                .build();
    }

    @Override
    public VehicleGenerationResponseDTO toResponse(VehicleGenerationEntity entity) {
        return VehicleGenerationResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .alias(entity.getAlias())
                .yearsFrom(entity.getYearFrom())
                .monthFrom(entity.getMonthFrom())
                .yearsTo(entity.getYearTo())
                .monthTo(entity.getMonthTo())
                .build();
    }
}
