package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UserVehicleResponseDTO {
    private Long id;
    private Long userId;
    private UUID userPublicId;
    private UUID particularVehicleId;
    private String vehicleBrandName;
    private String vehicleModelName;
    private String vehicleVariantName;
    private String vehicleGenerationName;
    private String vehicleEquipmentLevelName;
    private String engineCode;
    private String transmissionName;
    private String licencePlate;
    private Integer year;
    private BigDecimal currentKm;
    private String vin;
}
