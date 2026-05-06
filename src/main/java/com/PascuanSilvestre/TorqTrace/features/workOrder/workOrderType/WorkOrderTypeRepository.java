package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderTypeRepository extends JpaRepository<WorkOrderTypeEntity,Long> {
}
