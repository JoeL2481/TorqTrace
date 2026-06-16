package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class CompleteWorkOrderDTO {

    @NotBlank
    private String description;

}
