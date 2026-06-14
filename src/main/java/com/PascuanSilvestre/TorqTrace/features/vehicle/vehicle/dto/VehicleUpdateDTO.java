package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto;

import com.PascuanSilvestre.TorqTrace.common.utils.ValidationDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleUpdateDTO {
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
    private Integer transmissionCode;

    private VehicleBodyType vehicleBodyType;
    private VehicleCategory vehicleCategory;

    @AssertTrue(message = "Provide vehicleBrandId or vehicleBrandName, not both")
    public boolean isVehicleBrandValid() {
        return ValidationDTO.onlyOneOrNone(vehicleBrandId, vehicleBrandName);
    }

    @AssertTrue(message = "Provide vehicleModelId or vehicleModelName, not both")
    public boolean isVehicleModelValid() {
        return ValidationDTO.onlyOneOrNone(vehicleModelId, vehicleModelName);
    }

    @AssertTrue(message = "Provide vehicleGenerationId or vehicleGenerationName, not both")
    public boolean isVehicleGenerationValid() {
        return ValidationDTO.onlyOneOrNone(vehicleGenerationId, vehicleGenerationName);
    }

    @AssertTrue(message = "Provide vehicleVariantId or vehicleVariantName, not both")
    public boolean isVehicleVariantValid() {
        return ValidationDTO.onlyOneOrNone(vehicleVariantId, vehicleVariantName);
    }

    @AssertTrue(message = "Provide vehicleEquipmentLevelId or vehicleEquipmentLevelName, not both")
    public boolean isVehicleEquipmentLevelValid() {
        return ValidationDTO.onlyOneOrNone(vehicleEquipmentLevelId, vehicleEquipmentLevelName);
    }

    @AssertTrue(message = "Provide engineId or engineCode, not both")
    public boolean isEngineValid() {
        return ValidationDTO.onlyOneOrNone(engineId, engineCode);
    }

    @AssertTrue(message = "Provide transmissionId or transmissionCode, not both")
    public boolean isTransmissionValid() {
        return ValidationDTO.onlyOneOrNone(transmissionId, transmissionCode);
    }
}
