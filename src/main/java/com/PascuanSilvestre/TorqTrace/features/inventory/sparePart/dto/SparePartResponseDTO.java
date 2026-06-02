package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
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
public class SparePartResponseDTO {
    private Long id;
    private String name;
    private SparePartCategoryResponseDTO category;
    private String description;
}
