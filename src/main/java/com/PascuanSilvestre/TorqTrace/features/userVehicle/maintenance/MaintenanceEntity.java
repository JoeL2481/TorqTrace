package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name="maintenance")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MaintenanceEntity extends AuditableBase {


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_vehicle_id", nullable = false)
    private UserVehicleEntity userVehicle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_order_id", nullable = false)
    private WorkOrderEntity workshopOrder;

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
