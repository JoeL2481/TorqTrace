package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;



import java.math.BigDecimal;
@Entity
@Table(name = "user_vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserVehicleEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
    @NotBlank
    @Size(max = 20)
    @Column(name = "licence_plate", length = 20, nullable = false, unique = true)
    private String licencePlate;
    @NotNull
    @PositiveOrZero
    @Column(name = "current_km", precision = 10, scale = 2, nullable = false)
    private BigDecimal currentKm;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
