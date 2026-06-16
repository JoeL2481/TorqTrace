package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.mapper;

public interface IWorkshopClientMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
    void toEntityUpdate(UpdateDTO request, Entity entity);
}
