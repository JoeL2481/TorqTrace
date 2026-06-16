package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;
import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="item_work_order")
@Getter
@Setter
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
    private Double unitPrice;

    @Column(name = "subtotal")
    private Double subtotal;


}
