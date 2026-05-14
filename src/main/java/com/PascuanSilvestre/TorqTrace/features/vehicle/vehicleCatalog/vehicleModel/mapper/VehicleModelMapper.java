package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@AllArgsConstructor

public class VehicleModelMapper implements IMapper<VehicleModelEntity, VehicleModelRequestDTO, VehicleModelResponseDTO> {
   private final ModelMapper mapper;
    @Override
    public VehicleModelEntity toEntity(VehicleModelRequestDTO request) {
        return mapper.map(request, VehicleModelEntity.class);
    }

    @Override
    public VehicleModelResponseDTO toResponse(VehicleModelEntity vehicleModelEntity) {
        return mapper.map(vehicleModelEntity, VehicleModelResponseDTO.class);
    }
}
