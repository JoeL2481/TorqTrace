package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineRespository extends JpaRepository<EngineEntity, Long> {
    Optional<EngineEntity> findByCodeIgnoreCase(String code);
    boolean existsByCodeIgnoreCase(String code);
}
