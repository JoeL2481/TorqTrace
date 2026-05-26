package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineTransmissionRequestDTO {

    @NotNull(message = "Engine id is required")
    private Long engineId;

    @NotNull(message = "Transmission id is required")
    private Long transmissionId;
}
