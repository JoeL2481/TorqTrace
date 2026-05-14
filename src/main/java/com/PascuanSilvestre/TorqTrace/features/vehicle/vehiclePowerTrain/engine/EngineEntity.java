package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "engine")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EngineEntity extends AuditableBase {


    @Column(name="code", nullable = false)
    private String code;
    @Column(name="name" )
    private String name;
    @Column(name="displacement_cc")
    private Integer displacementCc;
    @Column(name="power_hp")
    private Integer powerHp;
    @Column(name="torque_nm")
    private Integer torqueNm;
    @Column(name="amount_cylinders")
    private Integer amountCylinders;
    @Enumerated(EnumType.STRING)
    @Column(name= "engine_layout", length = 20)
    private EngineLayout engineLayout;



}
