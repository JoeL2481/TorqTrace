package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRespository extends JpaRepository<EngineEntity, Long> {
}
