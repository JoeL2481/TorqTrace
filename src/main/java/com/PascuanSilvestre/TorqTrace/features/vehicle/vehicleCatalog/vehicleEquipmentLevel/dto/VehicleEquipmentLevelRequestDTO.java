package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VehicleEquipmentLevelRequestDTO {


    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String name;
}
