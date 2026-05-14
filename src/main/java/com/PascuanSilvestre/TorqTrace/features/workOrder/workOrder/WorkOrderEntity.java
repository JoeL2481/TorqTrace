package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;


import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.enums.WorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.WorkOrderTypeEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="work_order")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WorkOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workshop_id",nullable = false)
    private WorkShopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    private WorkShopClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_id",nullable = false)
    private VehicleEntity vehicle;


    @Column(name="entry_km",nullable = false)
    private Double entryKm;
    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", nullable = false)
    private WorkOrderStatus status;

/*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_vehicle_maitenance_id")
    private UserVehicleMaitenanceEntity userVehicleMaitenance;
*/


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workshop_order_type_id",nullable = false)
    private WorkOrderTypeEntity workshopOrderType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_work_order_id",nullable = false)
    private WorkOrderItemEntity workOrderItem;

    @Column(name="currency",length = 20)
    private String currency;
    @Column(name="labor_charge")
    private Double laborCharge;
    @Column(name="total_cost")
    private Double totalCost;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
