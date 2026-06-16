package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceDTO {
    private Long id;
    private String userVehicleId;
    private Long workOrderId;
    private MaintenanceType maintenanceType;
    private String description;
    private int serviceKm;
    private int nextServiceKm;
    private Date nextServiceDate;
}
