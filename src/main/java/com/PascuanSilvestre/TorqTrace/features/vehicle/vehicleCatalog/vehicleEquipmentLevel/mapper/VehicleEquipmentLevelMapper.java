package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleEquipmentLevelMapper implements IMapper<VehicleEquipmentLevelEntity, VehicleEquipmentLevelRequestDTO, VehicleEquipmentLevelResponseDTO> {

    private final ModelMapper modelMapper;


    @Override
    public VehicleEquipmentLevelEntity toEntity(VehicleEquipmentLevelRequestDTO request) {
        return modelMapper.map(request, VehicleEquipmentLevelEntity.class);

    }

    @Override
    public VehicleEquipmentLevelResponseDTO toResponse(VehicleEquipmentLevelEntity entity) {
        return  modelMapper.map(entity, VehicleEquipmentLevelResponseDTO.class);
    }
}
