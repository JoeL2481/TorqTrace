package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper;

public interface IWorkOrderMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
    void toEntityUpdate(UpdateDTO request, Entity entity);
}
