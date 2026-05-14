package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="item_work_order")
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkOrderItemEntity  extends AuditableBase {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_order_id",nullable = false)
    private WorkOrderEntity workOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="spare_part_id",nullable = false)
    private SparePartEntity sparePart;

    @Column(name = "quantity_requested",nullable = false)
    private Integer quantityRequested;

    @Column(name = "unit_price_at_time")
    private Double unitPriceAtTime;
    @Column(name = "price_at_execution")
    private Double priceAtExecution;
    @Column(name = "subtotal")
    private Double subtotal;


}
