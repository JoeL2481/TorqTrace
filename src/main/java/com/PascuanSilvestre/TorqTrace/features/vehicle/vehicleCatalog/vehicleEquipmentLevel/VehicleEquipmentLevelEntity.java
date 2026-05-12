package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "vehicle_equipment_level")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter

@Getter
public class VehicleEquipmentLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

}
