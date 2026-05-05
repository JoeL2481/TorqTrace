package com.PascuanSilvestre.TorqTrace.features.userVehicle.extraMaintenanceItems;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.ExtraMaintenanceReminderType;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "extra_maintenance_reminder")
public class ExtraMaintenanceReminderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_vehicle_id", nullable = false)
    private UserVehicleEntity userVehicle;

    @Enumerated(EnumType.STRING)
    @Column(name = "reminder_type", nullable = false, length = 50)
    private ExtraMaintenanceReminderType reminderType;



    @Column(name = "next_replacement_km")
    private int nextReplacementKm;

    @Column(name = "next_replacement_date")
    private int nextReplacementDate;

    @Column(name = "notes", length = 255)
    private String notes;


}