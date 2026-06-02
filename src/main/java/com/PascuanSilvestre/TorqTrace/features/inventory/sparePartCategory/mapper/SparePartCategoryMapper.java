package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SparePartCategoryMapper implements IMapper<SparePartCategoryEntity, SparePartCategoryCreateDTO, SparePartCategoryResponseDTO> {
    private final ModelMapper mapper;

    @Override
    public SparePartCategoryEntity toEntity(SparePartCategoryCreateDTO request) {
        return mapper.map(request, SparePartCategoryEntity.class);
    }

    @Override
    public SparePartCategoryResponseDTO toResponse(SparePartCategoryEntity entity) {
        return mapper.map(entity, SparePartCategoryResponseDTO.class);
    }

    public void toEntityUpdate(SparePartCategoryUpdateDTO request, SparePartCategoryEntity entity) {
        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }
}
