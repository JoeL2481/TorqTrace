package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.mapper;

public interface ISparePartMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);

    Entity toEntityUpdate(UpdateDTO request, Entity entity);

    ResponseDTO toResponse(Entity entity);
}
