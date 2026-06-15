package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartCompatibilityUpdateDTO {
    private Long sparePartId;

    private String vehicleId;

    private Long engineId;

    private Long transmissionId;

    @Size(max = 255, message = "Notes must not exceed 255 characters")
    private String notes;
}
