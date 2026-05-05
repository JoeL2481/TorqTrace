package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleFuelType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquimentLevel.VehicleEquimentLevelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name= "vehicle")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_brand", nullable = false)
    private VehicleBrandEntity vehicleBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_model", nullable = false)
    private VehicleModelEntity vehicleModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_variant", nullable = true)
    private VehicleVariantEntity vehicleVariant;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_generation", nullable = true)
    private VehicleGenerationEntity vehicleGeneration;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_equipment_level", nullable = true)
    private VehicleEquimentLevelEntity vehicleEquipmentLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_body_type", length = 50)
    private VehicleBodyType vehicleBodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_category", length = 50)
    private VehicleCategory vehicleCategory;





}
