package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper;

public interface IWorkShopMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO, DetailedResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
    void toEntityUpdate(UpdateDTO request, Entity entity);
    DetailedResponseDTO toDetailResponse(Entity entity);
    DetailedResponseDTO toDetailedResponse(Entity entity);
}
