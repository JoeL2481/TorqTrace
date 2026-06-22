package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopStockResponseDTO {

    private Long id;

    private SparePartResponseDTO sparePart;

    private Double Unitprice;

    private Integer stockQuantity;

    private Integer minStockAlert;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
