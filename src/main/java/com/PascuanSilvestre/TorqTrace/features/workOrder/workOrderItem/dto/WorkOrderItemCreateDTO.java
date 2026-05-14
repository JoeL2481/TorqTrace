package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderItemCreateDTO {

    @NotNull
    private Long workOrderId;

    @NotNull
    private Long sparePartId;

    @NotNull
    private Integer quantityRequested;

    private Double unitPriceAtTime;

    private Double priceAtExecution;

    private Double subtotal;
}
