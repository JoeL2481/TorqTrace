package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderItemRepository extends JpaRepository<WorkOrderItemEntity, Long> {
}
