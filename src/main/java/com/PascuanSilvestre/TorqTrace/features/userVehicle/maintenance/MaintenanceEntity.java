package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkShopOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workStatus.WorkOrderStatusEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="maintenance")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_vehicle_id", nullable = false)
    private UserVehicleEntity userVehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_order_id", nullable = false)
    private WorkShopOrderEntity workshopOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_type", nullable = false, length = 50)
    private MaintenanceType maintenanceType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "service_km")
    private int serviceKm;

    @Column(name = "next_service_km")
    private int nextServiceKm;

    @Column(name = "next_service_date")
    private Date next_service_date;

}
