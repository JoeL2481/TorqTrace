package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long> {
    List<WorkOrderEntity> findByWorkshopId(Long workshopId);
    Optional<WorkOrderEntity> findByIdAndWorkhopId(Long id,Long workshopId);
    List<WorkOrderEntity> findAllByOrderByCreatedAtDesc();
    List<WorkOrderEntity> findByWorkshopIdByOrderByCreatedAtDesc(Long workshopId);
}
