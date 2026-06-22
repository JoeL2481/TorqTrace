package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SparePartCompatibilityRepository extends JpaRepository<SparePartCompatibilityEntity, Long> {
    List<SparePartCompatibilityEntity> findBySparePartId(Long sparePartId);

    boolean existsByVehicleId(Long vehicleId);
}
