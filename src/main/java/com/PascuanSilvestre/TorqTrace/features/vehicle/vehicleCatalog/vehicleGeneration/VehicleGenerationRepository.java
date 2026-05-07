package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VehicleGenerationRepository extends JpaRepository<VehicleGenerationEntity,Long> {

    Optional<VehicleBrandEntity> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);


}
