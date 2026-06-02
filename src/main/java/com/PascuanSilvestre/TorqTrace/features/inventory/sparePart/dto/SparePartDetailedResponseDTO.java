package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityNestedResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartDetailedResponseDTO {
    private Long id;
    private String name;
    private SparePartCategoryResponseDTO category;
    private String description;
    private List<SparePartCompatibilityNestedResponseDTO> compatibilities;
}
