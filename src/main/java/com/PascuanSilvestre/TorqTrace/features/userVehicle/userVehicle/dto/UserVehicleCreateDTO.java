package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UserVehicleCreateDTO {
    @NotNull(message = "Vehicle configuration is required")
    private String particularVehicleId;

    @NotBlank(message = "Licence plate is required")
    @Size(max = 20, message = "Licence plate must not exceed 20 characters")
    private String licencePlate;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotNull(message = "Current km is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Current km must be greater than or equal to zero")
    private BigDecimal currentKm;

    @NotBlank(message = "Vin is required")
    @Size(max = 50, message = "Vin must not exceed 50 characters")
    private String vin;
}
