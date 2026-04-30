package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleFuelType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
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

@Enumerated(EnumType.STRING)
    @Column(name = "vehicle_body_type", length = 50)
    private VehicleBodyType vehicleBodyType;
    @Column(name = "vehicle_category", length = 50)
    private VehicleCategory vehicleCategory;
    @Column(name = "vehicle_fuel_type", length = 50)
    private VehicleFuelType vehicleFuelType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="brand_id", nullable = false)
    private VehicleBrandEntity brand;




}
