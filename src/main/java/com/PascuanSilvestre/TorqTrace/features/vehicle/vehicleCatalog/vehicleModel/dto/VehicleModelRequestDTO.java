package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.Valid;

@Setter
@Getter
public class VehicleModelRequestDTO {

    @NotBlank(message="Model name is required")
    @Size(max=100, message = "First name must not exxceed 100 characters")
    private String name;
}
