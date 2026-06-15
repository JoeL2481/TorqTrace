package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicleEntity, Long> {
    List<UserVehicleEntity> findByUserId(Long userId);

    Optional<UserVehicleEntity> findByIdAndUserId(Long id, Long userId);

    boolean existsByLicencePlate(String licencePlate);

    boolean existsByVin(String vin);

    boolean existsByLicencePlateAndIdNot(String licencePlate, Long id);

    boolean existsByVinAndIdNot(String vin, Long id);
}
