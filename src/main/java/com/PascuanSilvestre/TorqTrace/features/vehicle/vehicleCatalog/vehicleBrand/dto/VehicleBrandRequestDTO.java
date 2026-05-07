package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleBrandRequestDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
}
