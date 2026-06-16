package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto;

//import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderItemResponseDTO {

    private Long id;

  private SparePartResponseDTO sparePart;

    private Integer quantityRequested;

    private Double unitPriceAtTime;

    private Double priceAtExecution;

    private Double subtotal;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
