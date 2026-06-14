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
public class VehicleDetailedResponseDTO {

    private UUID publicId;

    private String vehicleBrandName;
    private String vehicleModelName;
    private String vehicleGenerationName;
    private String vehicleGenerationAlias;
    private String vehicleVariantName;
    private String vehicleEquipmentLevelName;

    private String engineCode;
    private String engineName;

    private String transmissionName;

    private VehicleBodyType vehicleBodyType;
    private VehicleCategory vehicleCategory;
}
