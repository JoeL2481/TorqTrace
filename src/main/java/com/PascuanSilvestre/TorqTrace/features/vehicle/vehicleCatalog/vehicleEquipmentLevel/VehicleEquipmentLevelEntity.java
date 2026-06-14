package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @OneToMany(mappedBy = "vehicleEquipmentLevel")
    private List<VehicleEntity> vehicleConfigurations;
}
