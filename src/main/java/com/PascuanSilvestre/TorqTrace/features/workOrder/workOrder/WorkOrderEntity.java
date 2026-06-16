package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;


import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.MaintenanceEntity;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.WorkOrderItemEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.WorkshopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Table(name="work_order")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class WorkOrderEntity  extends AuditableBase {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workshop_id",nullable = false)
    private WorkShopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    private WorkshopClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userVechicle_id",nullable = false)
    private UserVehicleEntity userVehicle;


    @Column(name="entry_km",nullable = false)
    private Double entryKm;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", nullable = false)
    private EWorkOrderStatus status;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="maitenance_id")
    private MaintenanceEntity maitenance;


    @Column(name= "work_order_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EWorkOrderType workshopOrderType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_work_order_id",nullable = false)
    private WorkOrderItemEntity workOrderItem;

    @Column(name="currency",length = 20)
    private String currency;
    @Column(name="labor_charge")
    private Double laborCharge;
    @Column(name="total_cost")
    private Double totalCost;


}
