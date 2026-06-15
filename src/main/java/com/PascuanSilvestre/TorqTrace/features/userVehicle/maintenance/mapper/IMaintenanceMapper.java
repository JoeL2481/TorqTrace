package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.mapper;

public interface IMaintenanceMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);

    Entity toEntityUpdate(UpdateDTO request, Entity entity);

    ResponseDTO toResponse(Entity entity);
}
