package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
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

    private Long workshopOrderTypeId;

    private String currency;

    private Double laborCharge;

    private Double totalCost;
}
