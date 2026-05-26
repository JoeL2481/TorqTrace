package com.PascuanSilvestre.TorqTrace.common.utils;

public interface IMapper<Entity, RequestOrCreateDTO, ResponseDTO> {
    Entity toEntity(RequestOrCreateDTO request);
    ResponseDTO toResponse(Entity entity);
}
