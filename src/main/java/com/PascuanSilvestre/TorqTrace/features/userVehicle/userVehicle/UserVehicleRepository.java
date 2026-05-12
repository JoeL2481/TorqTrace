package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicleEntity, Long> {
}
