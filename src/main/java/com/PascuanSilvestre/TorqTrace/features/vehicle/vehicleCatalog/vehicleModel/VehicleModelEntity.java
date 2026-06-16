package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_brand_id", nullable = false)
    private VehicleBrandEntity vehicleBrand;

    @OneToMany(mappedBy = "vehicleModel",fetch = FetchType.LAZY)
    private List<VehicleGenerationEntity> vehicleGenerations;

    @OneToMany(mappedBy = "vehicleModel",fetch = FetchType.LAZY)
    private List<VehicleEntity> vehicleConfigurations;

}
