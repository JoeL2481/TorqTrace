package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "transmission")
@NoArgsConstructor
@AllArgsConstructor
public class TransmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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


}
