package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.mapper;

public interface IWorkshopStaffMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> {
    Entity toEntity(CreateDTO request);
    ResponseDTO toResponse(Entity entity);
    void toEntityUpdate(UpdateDTO request, Entity entity);
}
