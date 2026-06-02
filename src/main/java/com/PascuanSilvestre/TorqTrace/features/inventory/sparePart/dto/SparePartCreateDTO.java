package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartCreateDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
}
