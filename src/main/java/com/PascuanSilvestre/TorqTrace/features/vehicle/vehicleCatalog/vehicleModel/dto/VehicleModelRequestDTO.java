package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleModelRequestDTO {

    @Positive(message = "Vehicle brand id must be greater than zero")
    private Long vehicleBrandId;

    @NotBlank(message="Model name is required")
    @Size(max=100, message = "First name must not exxceed 100 characters")
    private String name;
}
