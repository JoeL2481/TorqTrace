package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="item_work_order")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WorkOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_order_id",nullable = false)
    private WorkOrderEntity workShopOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="spare_part_id",nullable = false)
    private SparePartEntity sparePartEntity;

    @Column(name = "quantity_requested",nullable = false)
    private Integer quantityRequested;

    @Column(name = "unit_price_at_time")
    private Double unitPriceAtTime;
    @Column(name = "price_at_execution")
    private Double priceAtExecution;
    @Column(name = "subtotal")
    private Double subtotal;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
