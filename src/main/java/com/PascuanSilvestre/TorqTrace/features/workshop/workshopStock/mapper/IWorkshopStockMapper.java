package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.mapper;

public interface IWorkshopStockMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
    void toEntityUpdate(UpdateDTO request, Entity entity);
}
