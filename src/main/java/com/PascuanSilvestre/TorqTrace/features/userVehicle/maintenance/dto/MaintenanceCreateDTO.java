package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceCreateDTO {
    @NotNull(message = "Work order is required")
    private Long workOrderId;

    @NotNull(message = "Maintenance type is required")
    private MaintenanceType maintenanceType;

    private String description;
    private int serviceKm;
    private int nextServiceKm;
    private Date nextServiceDate;
}
