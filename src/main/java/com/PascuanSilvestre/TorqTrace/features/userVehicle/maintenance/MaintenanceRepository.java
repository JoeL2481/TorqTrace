package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
    List<MaintenanceEntity> findByUserVehicleUserId(Long userId);

    List<MaintenanceEntity> findByUserVehicleIdAndUserVehicleUserId(Long userVehicleId, Long userId);

    Optional<MaintenanceEntity> findByIdAndUserVehicleUserId(Long id, Long userId);

    void deleteByUserVehicleId(Long userVehicleId);
}
