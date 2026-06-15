package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.mapper;

public interface ISparePartCompatibilityMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);

    Entity toEntityUpdate(UpdateDTO request, Entity entity);

    ResponseDTO toResponse(Entity entity);
}
