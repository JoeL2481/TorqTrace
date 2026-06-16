package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshopStaffRepository extends JpaRepository<WorkshopStaffEntity,Long> {

    boolean existsByUserIdAndWorkshopId(Long userId,Long workshopId);
    Optional<WorkshopStaffEntity> findByUserIdAndWorkshopId(Long userId, Long workshopId);

}
