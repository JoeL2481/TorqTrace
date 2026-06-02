package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleCreateDTO {
    @NotNull(message = "Vehicle brand id is required")
    private Long vehicleBrandId;

    @NotNull(message = "Vehicle model id is required")
    private Long vehicleModelId;

    private Long vehicleVariantId;

    private Long vehicleGenerationId;

    private Long vehicleEquipmentLevelId;

    @NotNull(message = "Vehicle body type is required")
    private VehicleBodyType vehicleBodyType;

    @NotNull(message = "Vehicle category is required")
    private VehicleCategory vehicleCategory;
}
