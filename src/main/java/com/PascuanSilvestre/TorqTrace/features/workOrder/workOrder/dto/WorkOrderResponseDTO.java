package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto;

import com.PascuanSilvestre.TorqTrace.features.workOrder.enums.WorkOrderStatus;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderResponseDTO {

    private Long id;

    private WorkShopClientResponseDTO client;

    //private VehicleResponseDTO vehicle;

    private Double entryKm;

    private String description;

    private WorkOrderStatus status;

    private WorkOrderTypeResponseDTO workOrderType;

    private WorkOrderItemResponseDTO workOrderItem;

    private String currency;

    private Double laborCharge;

    private Double totalCost;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
