package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.AspirationType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.EngineLayout;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "engine")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
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
    @Enumerated(EnumType.STRING)
    @Column(name= "aspiration_type", length =20)
    private AspirationType aspirationType;

    @OneToMany(mappedBy = "engine", fetch = FetchType.LAZY)
    private List<VehicleEntity> vehicleConfigurations;

}
