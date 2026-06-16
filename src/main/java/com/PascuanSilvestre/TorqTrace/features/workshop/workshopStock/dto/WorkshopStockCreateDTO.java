package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopStockCreateDTO {


    @NotNull(message = "Spare part ID is required")
    @Positive(message = "Spare part ID must be greater than 0")
    private Long sparePartId;

    @NotNull(message = "Workshop ID is required")
    @Positive(message = "Spare part ID must be greater than 0")
    private Long workshopId;

    @NotNull(message = "Unit price is required")
    @PositiveOrZero(message = "Unit price cannot be negative")
    private Double unitPrice;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 1, message = "Stock quantity must be at least 1")
    private Integer stockQuantity;

    @NotNull(message = "Minimum stock alert is required")
    @PositiveOrZero(message = "Minimum stock alert cannot be negative")
    private Integer minStockAlert;
}
