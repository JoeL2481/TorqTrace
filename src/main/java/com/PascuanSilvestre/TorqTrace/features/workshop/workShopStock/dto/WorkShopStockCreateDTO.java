package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopStockCreateDTO {

    @NotNull
    private Long workshopId;

    @NotNull
    private Long sparePartId;

    @NotNull
    @Min(1)
    private Integer stockQuantity;

    @NotNull
    @Min(0)
    private Integer minStockAlert;}
