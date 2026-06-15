package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import java.util.List;

public interface IMaintenanceService<CreateDTO, UpdateDTO, ResponseDTO, UserVehicleID, ID> {
    ResponseDTO create(UserVehicleID userVehicleId, CreateDTO request);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO getById(ID id);
    List<ResponseDTO> getAll();
    List<ResponseDTO> getMaintenancesByUserVehicle(UserVehicleID userVehicleId);
    void delete(ID id);
}
