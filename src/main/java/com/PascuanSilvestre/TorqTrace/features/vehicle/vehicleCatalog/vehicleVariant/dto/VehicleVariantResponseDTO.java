package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto;

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
public class VehicleVariantResponseDTO {
    private Long id;
    private String name;
}
