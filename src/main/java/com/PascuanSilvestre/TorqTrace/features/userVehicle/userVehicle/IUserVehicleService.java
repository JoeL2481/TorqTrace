package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import java.util.List;

public interface IUserVehicleService<CreateDTO, UpdateDTO, ResponseDTO, ID, Entity> {
    ResponseDTO create(CreateDTO request);
    ResponseDTO update(ID id, UpdateDTO request);
    void incrementCurrentMileage(Entity userVehicle, int serviceKm);
    ResponseDTO getById(ID id);
    Entity getOwnedVehicleOnly(ID publicId);
    Entity getAnyVehicleById(ID publicId);
    List<ResponseDTO> getAll();
    void delete(ID id);






}
