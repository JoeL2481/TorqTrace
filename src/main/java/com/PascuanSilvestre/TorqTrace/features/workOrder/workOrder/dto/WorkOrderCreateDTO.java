package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.enums.WorkOrderStatus;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderCreateDTO {

    @NotNull
    private Long workshopId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private Double entryKm;

    private String description;

    @NotNull
    private WorkOrderStatus status;

    @NotNull
    private Long workshopOrderTypeId;

    @NotNull
    private Long workOrderItemId;

    private String currency;

    private Double laborCharge;

    private Double totalCost;
}
