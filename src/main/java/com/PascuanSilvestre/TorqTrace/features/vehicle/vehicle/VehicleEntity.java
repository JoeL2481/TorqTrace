package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name= "vehicle")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity  extends AuditableBase {

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "public_id", nullable = false, unique = true, updatable = false, length = 36)
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_brand_id", nullable = false)
    private VehicleBrandEntity vehicleBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_model_id", nullable = false)
    private VehicleModelEntity vehicleModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_variant_id", nullable = true)
    private VehicleVariantEntity vehicleVariant;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_generation_id", nullable = true)
    private VehicleGenerationEntity vehicleGeneration;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_equipment_level_id", nullable = true)
    private VehicleEquipmentLevelEntity vehicleEquipmentLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_body_type_id", length = 50)
    private VehicleBodyType vehicleBodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_category_id", length = 50)
    private VehicleCategory vehicleCategory;
}
