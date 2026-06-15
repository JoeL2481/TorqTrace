package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.mapper;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateCompleteDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;

public interface IVehicleMapper<Entity, CreateDTO, CompleteCreateDTO, ResponseDTO, DetailedResponseDTO> {
    Entity toEntity(CreateDTO dto);

    Entity toCompleteEntity(CompleteCreateDTO dto);

    ResponseDTO toResponse(Entity entity);

    DetailedResponseDTO toDetailedResponse(Entity entity);
}
