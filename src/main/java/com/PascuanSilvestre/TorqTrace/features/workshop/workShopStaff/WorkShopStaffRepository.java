package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShopStaffRepository extends JpaRepository<WorkShopStaffEntity,Long> {
}
