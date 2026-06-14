package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkShopStaffRepository extends JpaRepository<WorkShopStaffEntity,Long> {

    List<WorkShopStaffEntity> findByWorkshopId(Long workshopId);
    boolean existsByUserIdAndWorkshopId(Long userId,Long workshopId);
    Optional<WorkShopStaffEntity> findByUserIdAndWorkshopId(Long userId, Long workshopId);

}
