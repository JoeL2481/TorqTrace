package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartCompatibilityResponseDTO {
    private Long id;
    private Long sparePartId;
    private String vehicleId;
    private Long engineId;
    private Long transmissionId;
    private String notes;
}
