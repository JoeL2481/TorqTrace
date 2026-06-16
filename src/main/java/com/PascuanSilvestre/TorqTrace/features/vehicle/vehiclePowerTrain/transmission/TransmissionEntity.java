package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission;

import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transmission")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransmissionEntity extends AuditableBase {



    @Column(name="name")
    private String name;
    @Column(name="code", nullable=false, unique=true)
    private int code;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;
    @Column(name="gears_amount")
    private int gears;
    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="description")
    private String description;


    @OneToMany(mappedBy = "transmission", fetch = FetchType.LAZY)
    private List<VehicleEntity> vehicleConfigurations;
}
