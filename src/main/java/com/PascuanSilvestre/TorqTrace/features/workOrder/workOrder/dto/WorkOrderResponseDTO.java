package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums.EWorkOrderType;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderResponseDTO {

    private Long id;


    private WorkShopResponseDTO workshop;

    private WorkshopClientResponseDTO client;

    private UserVehicleResponseDTO userVehicle;

    private Double entryKm;

    private String description;

    private EWorkOrderStatus status;

    private EWorkOrderType workOrderType;

    private WorkOrderItemResponseDTO workOrderItem;

    private MaintenanceDTO maintenance;

    private String currency;

    private Double laborCharge;

    private Double totalCost;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
