package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.ECurrency;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class WorkOrderCreateDTO {

    @NotNull
    private Long workshopId;

    @NotNull
    private String userVehicleId;

    @NotNull
    @Min(0)
    private Integer entryKm;


    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;


    private EWorkOrderStatus status;

    @NotNull
    private EWorkOrderType workOrderType;

    @NotNull
    private ECurrency currency;

    private List<WorkOrderItemCreateDTO> WorkOrderitems;

    @NotNull(message = "Labor charge is required")
    @PositiveOrZero(message = "Labor charge cannot be negative")
    private Double laborCharge;

}
