package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopStockResponseDTO {

    private Long id;

    //private SparePartResponseDTO sparePart;

    private Integer stockQuantity;

    private Integer minStockAlert;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
