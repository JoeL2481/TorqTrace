package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceCreateDTO {
    @NotNull(message = "Work order is required")
    private Long workOrderId;

    @NotNull(message = "Maintenance type is required")
    private MaintenanceType maintenanceType;

    @Size(max = 500, message = "Description can have at most 500 characters")
    private String description;

    @Min(value = 1, message = "Service km must be greater than zero")
    private int serviceKm;

    @Min(value = 0, message = "Next service km cannot be negative")
    private int nextServiceKm;

    private Date nextServiceDate;
}
