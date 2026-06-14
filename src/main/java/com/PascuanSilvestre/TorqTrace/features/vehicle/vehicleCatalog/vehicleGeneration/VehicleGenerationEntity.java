package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="vehicle_generation")
public class VehicleGenerationEntity  extends AuditableBase {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "year_from", nullable = false)
    private Integer yearFrom;

    @Column(name = "month_from")
    private Integer monthFrom;

    @Column(name = "year_to")
    private Integer yearTo;

    @Column(name = "month_to")
    private Integer monthTo;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="vehicle_model_id", nullable = false)
    private VehicleModelEntity vehicleModel;

    @OneToMany(mappedBy = "vehicleGeneration",fetch = FetchType.LAZY)
    private List<VehicleEntity> vehicleConfigurations ;


}
