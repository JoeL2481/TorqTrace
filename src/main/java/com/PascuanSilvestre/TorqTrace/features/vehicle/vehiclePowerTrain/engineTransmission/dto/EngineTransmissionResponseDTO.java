package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineTransmissionResponseDTO {
    private Long id;
    private EngineResponseDTO engine;
    private TransmissionResponseDTO transmission;
}
