package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.AspirationType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
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
public class EngineResponseDTO {

    private Long id;
    private String code;
    private String name;
    private Integer displacementCc;
    private Integer powerHp;
    private Integer torqueNm;
    private Integer amountCylinders;
    private EngineLayout engineLayout;
    private AspirationType aspirationType;
}
