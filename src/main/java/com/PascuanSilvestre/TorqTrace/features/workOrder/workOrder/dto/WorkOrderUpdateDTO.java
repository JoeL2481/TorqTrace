package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.enums.WorkOrderStatus;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderUpdateDTO {

    private Long workshopId;

    private Long clientId;

    private Long vehicleId;

    private Double entryKm;

    private String description;

    private WorkOrderStatus status;

    private Long workshopOrderTypeId;

    private Long workOrderItemId;

    private String currency;

    private Double laborCharge;

    private Double totalCost;
}
