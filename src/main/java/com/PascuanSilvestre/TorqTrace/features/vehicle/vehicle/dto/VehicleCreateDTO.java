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
public class VehicleCreateDTO {

    private Long vehicleBrandId;
    private String vehicleBrandName;

    private Long vehicleModelId;
    private String vehicleModelName;

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
}
