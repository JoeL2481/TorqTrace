package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;

import java.util.List;

public class VehicleModelService implements ICrudService<VehicleModelRequestDTO,VehicleModelRequestDTO,VehicleModelEntity>  {
    @Override
    public VehicleModelRequestDTO create(VehicleModelRequestDTO request) {
        return null;
    }

    @Override
    public List<VehicleModelRequestDTO> getAll() {
        return List.of();
    }

    @Override
    public VehicleModelRequestDTO getById(VehicleModelEntity vehicleModelEntity) {
        return null;
    }

    @Override
    public VehicleModelRequestDTO update(VehicleModelEntity vehicleModelEntity, VehicleModelRequestDTO request) {
        return null;
    }

    @Override
    public void delete(VehicleModelEntity vehicleModelEntity) {

    }
}
