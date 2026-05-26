package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.AspirationType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
