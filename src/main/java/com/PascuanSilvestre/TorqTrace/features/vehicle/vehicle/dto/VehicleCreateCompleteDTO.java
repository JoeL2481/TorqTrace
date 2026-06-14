package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto;

import com.PascuanSilvestre.TorqTrace.common.utils.ValidationDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleCreateCompleteDTO {

    private Long vehicleBrandId;
    private String vehicleBrandName;

    private Long vehicleModelId;
    private String vehicleModelName;

    private Long vehicleGenerationId;
    private String vehicleGenerationName;

    private Long vehicleVariantId;
    private String vehicleVariantName;

    private Long vehicleEquipmentLevelId;
    private String vehicleEquipmentLevelName;

    private Long engineId;
    private String engineCode;

    private Long transmissionId;
    private String transmissionName;

    @NotNull(message = "Vehicle body type is required")
    private VehicleBodyType vehicleBodyType;

    @NotNull(message = "Vehicle category is required")
    private VehicleCategory vehicleCategory;

    @AssertTrue(message = "Provide either vehicleBrandId or vehicleBrandName")
    public boolean isVehicleBrandValid() {
        return ValidationDTO.exactlyOne(vehicleBrandId, vehicleBrandName);
    }

    @AssertTrue(message = "Provide either vehicleModelId or vehicleModelName")
    public boolean isVehicleModelValid() {

        return ValidationDTO.exactlyOne(vehicleModelId, vehicleModelName);
    }

    @AssertTrue(message = "Provide either vehicleGenerationId or vehicleGenerationName")
    public boolean isVehicleGenerationValid() {
        return ValidationDTO.exactlyOne(vehicleGenerationId, vehicleGenerationName);
    }

    @AssertTrue(message = "Provide either vehicleVariantId or vehicleVariantName")
    public boolean isVehicleVariantValid() {

        return ValidationDTO.exactlyOne(vehicleVariantId, vehicleVariantName);
    }

    @AssertTrue(message = "Provide either vehicleEquipmentLevelId or vehicleEquipmentLevelName")
    public boolean isVehicleEquipmentLevelValid() {
        return ValidationDTO.exactlyOne(vehicleEquipmentLevelId, vehicleEquipmentLevelName);
    }

    @AssertTrue(message = "Provide either engineId or engineCode")
    public boolean isEngineValid() {

        return ValidationDTO.exactlyOne(engineId, engineCode);
    }

    @AssertTrue(message = "Provide either transmissionId or transmissionName")
    public boolean isTransmissionValid() {
        return ValidationDTO.exactlyOne(transmissionId, transmissionName);
    }


}
