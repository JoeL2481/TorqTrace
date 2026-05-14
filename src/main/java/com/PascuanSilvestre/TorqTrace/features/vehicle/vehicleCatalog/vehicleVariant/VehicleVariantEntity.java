package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant;

import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name= "vehicle_variant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VehicleVariantEntity extends AuditableBase {



    @Column(name = "name")
    private String name;
}
