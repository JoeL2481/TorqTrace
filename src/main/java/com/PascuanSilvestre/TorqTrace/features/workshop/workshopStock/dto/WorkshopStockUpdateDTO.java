package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopStockUpdateDTO {
    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.00", message = "Unit price cannot be negative")
    private Double unitPrice;

    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Minimum stock alert is required")
    @PositiveOrZero(message = "Minimum stock alert cannot be negative")
    private Integer minStockAlert;
}
