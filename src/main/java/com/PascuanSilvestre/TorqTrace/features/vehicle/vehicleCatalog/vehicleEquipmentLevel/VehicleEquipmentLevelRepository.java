package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleEquipmentLevelRepository extends JpaRepository<VehicleEquipmentLevelEntity, Long  > {
    Optional<VehicleEquipmentLevelEntity> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
