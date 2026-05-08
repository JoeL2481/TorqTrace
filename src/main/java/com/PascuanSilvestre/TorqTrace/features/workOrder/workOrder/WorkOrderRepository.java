package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrderEntity, Long> {
}
