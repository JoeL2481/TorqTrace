package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    private String publicId;

    private UUID vehicleBrandId;
    private Long vehicleModelId;
    private Long vehicleGenerationId;
    private Long vehicleVariantId;
    private Long vehicleEquipmentLevelId;
    private Long engineId;
    private Long transmissionId;

    private VehicleBodyType vehicleBodyType;
    private VehicleCategory vehicleCategory;
}
