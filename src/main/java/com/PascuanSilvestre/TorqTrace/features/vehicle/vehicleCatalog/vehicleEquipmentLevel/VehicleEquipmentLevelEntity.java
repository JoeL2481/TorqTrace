package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name= "vehicle_equipment_level")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter

@Getter
public class VehicleEquipmentLevelEntity  extends AuditableBase {


    @Column(name = "name",nullable = false)
    private String name;

}
