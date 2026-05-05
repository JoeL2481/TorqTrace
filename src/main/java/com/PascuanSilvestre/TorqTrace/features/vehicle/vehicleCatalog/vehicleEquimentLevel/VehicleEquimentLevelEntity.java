package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquimentLevel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "vehicle_equipment_level")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VehicleEquimentLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

}
