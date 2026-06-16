package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderUpdateDTO {

    private Double entryKm;

    private String description;

    private EWorkOrderStatus status;

    private EWorkOrderType workshopOrderType;

    private String currency;

    private Double laborCharge;

    private Double totalCost;
}
