package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import java.util.List;

public interface ICrudService<Req, Res, ID> {
    Res create(Req request);
    List<Res> getAll();
    Res getById(ID id);
    Res update(ID id, Req request);
    void delete(ID id);
}