package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.AspirationType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineCreateDTO {

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Displacement is required")
    @Min(value = 1, message = "Displacement must be greater than 0")
    @Max(value = 20000, message = "Displacement is too large")
    private Integer displacementCc;

    @NotNull(message = "Power HP is required")
    @Min(value = 1, message = "Power HP must be greater than 0")
    @Max(value = 5000, message = "Power HP is too large")
    private Integer powerHp;

    @NotNull(message = "Torque Nm is required")
    @Min(value = 1, message = "Torque Nm must be greater than 0")
    @Max(value = 10000, message = "Torque Nm is too large")
    private Integer torqueNm;

    @NotNull(message = "Amount of cylinders is required")
    @Min(value = 1, message = "Amount of cylinders must be at least 1")
    @Max(value = 16, message = "Amount of cylinders is too large")
    private Integer amountCylinders;

    @NotNull(message = "Engine layout is required")
    private EngineLayout engineLayout;

    @NotNull(message = "Aspiration type is required")
    private AspirationType aspirationType;
}
