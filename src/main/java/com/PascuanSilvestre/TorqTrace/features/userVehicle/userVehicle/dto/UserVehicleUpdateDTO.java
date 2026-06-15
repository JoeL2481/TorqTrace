package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UserVehicleUpdateDTO {
    private UUID particularVehicleId;

    @Size(max = 20, message = "Licence plate must not exceed 20 characters")
    private String licencePlate;

    private Integer year;

    @DecimalMin(value = "0.0", inclusive = true, message = "Current km must be greater than or equal to zero")
    private BigDecimal currentKm;

    @Size(max = 50, message = "Vin must not exceed 50 characters")
    private String vin;
}
