package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.enums.EMaintenanceType;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WorkOrderUpdateDTO {

    private Integer entryKm;

    private String description;

    private EWorkOrderStatus status;

    @Min(value = 0, message = "Next service km cannot be negative")
    private Integer nextServiceKm;
    private Date nextServiceDate;
    private EMaintenanceType maintenanceType;
    @AssertTrue(message = "maintenanceType, nextServiceKm and nextServiceDate are required when status is COMPLETED")
    public boolean isMaintenanceDataValid() {

        if (status == EWorkOrderStatus.COMPLETED) {
            return maintenanceType != null
                    && nextServiceKm != null
                    && nextServiceDate != null;
        }

        return true;
    }

    private EWorkOrderType workshopOrderType;

    private String currency;

    private Double laborCharge;

    private Double totalCost;
}
