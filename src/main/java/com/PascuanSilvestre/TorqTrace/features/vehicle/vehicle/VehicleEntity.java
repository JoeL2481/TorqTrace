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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

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
@Enumerated(EnumType.STRING)
    @Column(name = "vehicle_category", length = 50)
    private VehicleCategory vehicleCategory;
@Enumerated(EnumType.STRING)
    @Column(name = "vehicle_fuel_type", length = 50)
    private VehicleFuelType vehicleFuelType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="brand_id", nullable = false)
    private VehicleBrandEntity brand;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;



}
