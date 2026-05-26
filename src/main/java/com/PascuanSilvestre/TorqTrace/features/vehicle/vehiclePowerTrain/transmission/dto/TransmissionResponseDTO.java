package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.TransmissionType;
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
public class TransmissionResponseDTO {
    private Long id;
    private String name;
    private Integer code;
    private TransmissionType transmissionType;
    private Integer gears;
    private String manufacturer;
    private String description;
}
