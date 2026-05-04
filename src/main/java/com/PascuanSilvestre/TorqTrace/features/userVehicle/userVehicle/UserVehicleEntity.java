package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_vehicle")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "licence_plate", length = 20, nullable = false, unique = true)
    private String licencePlate;

    @Column(name ="year", nullable = false)
    private Integer year;

    @Column(name = "current_km", precision = 10, scale = 2, nullable = false)
    private BigDecimal currentKm;

    @Column(name="vin", nullable = false, length = 50, unique = true)
    private String vin;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name= "vehicle", nullable = false)
    private VehicleEntity vehicle;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
