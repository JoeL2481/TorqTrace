package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartCategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
}
