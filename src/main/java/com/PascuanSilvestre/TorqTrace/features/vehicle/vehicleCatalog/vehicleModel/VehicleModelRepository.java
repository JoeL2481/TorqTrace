package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModelEntity, Long> {
    Optional<VehicleModelEntity> findByNameIgnoreCase(String name);
}
