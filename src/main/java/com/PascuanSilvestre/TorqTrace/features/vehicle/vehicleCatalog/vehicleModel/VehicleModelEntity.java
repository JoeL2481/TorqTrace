package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vehicle_model")
@Setter
@Getter


public class VehicleModelEntity extends AuditableBase  {

    @Column(name = "name")
    private String name;

}
