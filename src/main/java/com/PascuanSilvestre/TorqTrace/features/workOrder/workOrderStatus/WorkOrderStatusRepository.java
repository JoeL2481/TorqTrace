package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderStatusRepository extends JpaRepository<WorkOrderStatusEntity,Long> {
}
