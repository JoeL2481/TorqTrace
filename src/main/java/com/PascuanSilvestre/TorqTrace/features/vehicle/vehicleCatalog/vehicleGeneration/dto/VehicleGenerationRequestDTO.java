package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleGenerationRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 150, message = "Alias must not exceed 150 characters")
    private String alias;

    @NotNull(message = "Year from is required")
    @Min(value = 1940, message = "Year from must be valid")
    @Max(value = 2100, message = "Year from must be valid")
    private Integer yearsFrom;

    @Min(value = 1, message = "Month from must be between 1 and 12")
    @Max(value = 12, message = "Month from must be between 1 and 12")
    private Integer monthFrom;

    @Min(value = 1940, message = "Year to must be valid")
    @Max(value = 2100, message = "Year to must be valid")
    private Integer yearsTo;

    @Min(value = 1, message = "Month to must be between 1 and 12")
    @Max(value = 12, message = "Month to must be between 1 and 12")
    private Integer monthTo;
}
