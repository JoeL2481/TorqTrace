package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicleEntity, Long> {
    List<UserVehicleEntity> findByUserIdAndDeletedFalse(Long userId);

    Optional<UserVehicleEntity> findByPublicIdAndUserIdAndDeletedFalse(String publicId, Long userId);

    Optional<UserVehicleEntity> findByPublicIdAndDeletedFalse(String publicId);

    boolean existsByLicencePlateAndDeletedFalse(String licencePlate);

    boolean existsByVinAndDeletedFalse(String vin);

    boolean existsByPublicIdAndDeletedFalse(String publicId);

    boolean existsByLicencePlateAndIdNotAndDeletedFalse(String licencePlate, Long id);

    boolean existsByVinAndIdNotAndDeletedFalse(String vin, Long id);

    boolean existsByParticularVehicleId(Long particularVehicleId);
}
