package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrandEntity, Long> {
    Optional<VehicleBrandEntity> findByNameIgnoreCase(String name);

    Optional<VehicleBrandEntity> findByPublicId(UUID publicId);

    boolean existsByNameIgnoreCase(String name);
}