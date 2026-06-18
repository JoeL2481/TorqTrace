package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.EMaintenanceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private EMaintenanceType EMaintenanceType;

    @Size(max = 500, message = "Description can have at most 500 characters")
    private String description;

    @Min(value = 1, message = "Service km must be greater than zero")
    private Integer serviceKm;

    @Min(value = 0, message = "Next service km cannot be negative")
    private Integer nextServiceKm;


    private Date nextServiceDate;
}
