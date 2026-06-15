package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleVariantRepository extends JpaRepository<VehicleVariantEntity, Long> {
    Optional<VehicleVariantEntity> findByNameIgnoreCase(String name);
}
