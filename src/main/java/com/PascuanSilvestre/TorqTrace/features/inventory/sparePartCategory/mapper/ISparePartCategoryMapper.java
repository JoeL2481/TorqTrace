package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.mapper;

public interface ISparePartCategoryMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);

    Entity toEntityUpdate(UpdateDTO request, Entity entity);

    ResponseDTO toResponse(Entity entity);
}
