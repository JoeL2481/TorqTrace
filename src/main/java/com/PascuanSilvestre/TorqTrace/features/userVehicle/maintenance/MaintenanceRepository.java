package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {
    List<MaintenanceEntity> findByUserVehicleUserId(Long userId);

    List<MaintenanceEntity> findByUserVehiclePublicId(String userVehicleId);

    List<MaintenanceEntity> findByUserVehiclePublicIdAndUserVehicleUserId(String userVehicleId, Long userId);

    Optional<MaintenanceEntity> findByIdAndUserVehicleUserId(Long id, Long userId);

    Optional<MaintenanceEntity> findByWorkshopOrderId(Long workOrderId);

    void deleteByUserVehicleId(Long userVehicleId);
}
