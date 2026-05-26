package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.AspirationType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineUpdateDTO {

    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Min(value = 1, message = "Displacement must be greater than 0")
    @Max(value = 20000, message = "Displacement is too large")
    private Integer displacementCc;

    @Min(value = 1, message = "Power HP must be greater than 0")
    @Max(value = 5000, message = "Power HP is too large")
    private Integer powerHp;

    @Min(value = 1, message = "Torque Nm must be greater than 0")
    @Max(value = 10000, message = "Torque Nm is too large")
    private Integer torqueNm;

    @Min(value = 1, message = "Amount of cylinders must be at least 1")
    @Max(value = 16, message = "Amount of cylinders is too large")
    private Integer amountCylinders;

    private EngineLayout engineLayout;

    private AspirationType aspirationType;
}
