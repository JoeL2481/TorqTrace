package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class UserVehicleResponseDTO {
    private String publicId;
    private Long userId;
    private java.util.UUID userPublicId;
    private String particularVehicleId;
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
