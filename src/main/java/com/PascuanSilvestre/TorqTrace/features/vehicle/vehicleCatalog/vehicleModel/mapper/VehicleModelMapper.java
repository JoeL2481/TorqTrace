package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleModelMapper implements IMapper<VehicleModelEntity, VehicleModelRequestDTO, VehicleModelResponseDTO> {
    @Override
    public VehicleModelEntity toEntity(VehicleModelRequestDTO request) {
        VehicleModelEntity entity = new VehicleModelEntity();
        entity.setName(request.getName());
        return entity;
    }

    @Override
    public VehicleModelResponseDTO toResponse(VehicleModelEntity vehicleModelEntity) {
        return VehicleModelResponseDTO.builder()
                .id(vehicleModelEntity.getId())
                .name(vehicleModelEntity.getName())
                .build();
    }
}
