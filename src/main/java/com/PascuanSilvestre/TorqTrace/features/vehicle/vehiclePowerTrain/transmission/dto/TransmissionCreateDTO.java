package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.enums.TransmissionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransmissionCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Code is required")
    @Min(value = 1, message = "Code must be greater than 0")
    private Integer code;

    @NotNull(message = "Transmission type is required")
    private TransmissionType transmissionType;

    @NotNull(message = "Gears amount is required")
    @Min(value = 1, message = "Gears amount must be at least 1")
    @Max(value = 12, message = "Gears amount is too large")
    private Integer gears;

    @NotBlank(message = "Manufacturer is required")
    @Size(max = 255, message = "Manufacturer must not exceed 255 characters")
    private String manufacturer;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
}
