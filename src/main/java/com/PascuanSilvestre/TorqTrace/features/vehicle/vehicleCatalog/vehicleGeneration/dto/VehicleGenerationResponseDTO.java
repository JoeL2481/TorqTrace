package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleGenerationResponseDTO {

    private Long id;
    private String name;
    private String alias;
    private Integer yearsFrom;
    private Integer monthFrom;
    private Integer yearsTo;
    private Integer monthTo;


}
