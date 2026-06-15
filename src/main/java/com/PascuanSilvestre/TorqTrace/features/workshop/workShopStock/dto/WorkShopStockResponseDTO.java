package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopStockResponseDTO {

    private Long id;

    private SparePartResponseDTO sparePart;

    private Integer stockQuantity;

    private Integer minStockAlert;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
