package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderItemCreateDTO {

    @NotNull
    @Min(1)
    private Long sparePartId;

    @NotNull
    @Min(1)
    private Integer quantityRequested;
}
