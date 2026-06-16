package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.mapper;

public interface IWorkOrderItemMapper<Entity, CreateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
}
