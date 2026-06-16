package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.MaintenanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class CompleteWorkOrderDTO {

    @NotBlank
    private String description;

    @NotNull
    private MaintenanceType maintenanceType;

    @Positive
    private int serviceKm;

    @PositiveOrZero
    private int nextServiceKm;

    @NotNull
    private Date nextServiceDate;

}
