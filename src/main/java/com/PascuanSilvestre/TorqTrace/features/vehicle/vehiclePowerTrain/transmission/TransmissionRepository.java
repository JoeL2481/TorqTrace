package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransmissionRepository extends JpaRepository<TransmissionEntity, Long> {
    Optional<TransmissionEntity> findByNameIgnoreCase(String name);
}
