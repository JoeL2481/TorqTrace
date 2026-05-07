package com.PascuanSilvestre.TorqTrace.features.userVehicle.extraMaintenanceItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraMaintenanceReminderRepository extends JpaRepository<ExtraMaintenanceReminderEntity, Long> {
}
