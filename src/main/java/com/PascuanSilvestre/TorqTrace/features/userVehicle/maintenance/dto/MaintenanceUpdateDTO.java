package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.EMaintenanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceUpdateDTO {
    private Long workOrderId;
    private EMaintenanceType EMaintenanceType;
    private String description;
    private Integer serviceKm;
    private Integer nextServiceKm;
    private Date nextServiceDate;
}
