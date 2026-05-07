package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "vehicle_variant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleVariantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name")
    private String name;
}
