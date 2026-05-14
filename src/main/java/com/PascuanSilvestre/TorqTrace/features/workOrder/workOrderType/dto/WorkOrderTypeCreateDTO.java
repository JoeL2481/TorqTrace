package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderTypeCreateDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
}
