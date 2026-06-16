package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehicle_brand")
public class VehicleBrandEntity extends AuditableBase {

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "public_id", nullable = false, unique = true, updatable = false, length = 36)
    private UUID publicId;

    @Column(name = "name", length = 255)
    private String name;

    @OneToMany(mappedBy = "vehicleBrand")
    private List<VehicleModelEntity> vehicleModels;

    @OneToMany(mappedBy = "vehicleBrand")
    private List<VehicleEntity>vehicleConfigurations;



}
