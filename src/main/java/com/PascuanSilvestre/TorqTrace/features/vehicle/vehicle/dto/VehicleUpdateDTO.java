package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleUpdateDTO {
    private Long vehicleBrandId;
    private Long vehicleModelId;
    private Long vehicleVariantId;
    private Long vehicleGenerationId;
    private Long vehicleEquipmentLevelId;
    private VehicleBodyType vehicleBodyType;
    private VehicleCategory vehicleCategory;
}
