package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.mapper;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SparePartMapper implements ISparePartMapper<SparePartEntity, SparePartCreateDTO, SparePartUpdateDTO, SparePartResponseDTO> {

    @Override
    public SparePartEntity toEntity(SparePartCreateDTO request) {
        SparePartEntity entity = new SparePartEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    @Override
    public SparePartResponseDTO toResponse(SparePartEntity entity) {
        SparePartResponseDTO response = new SparePartResponseDTO();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());

        if (entity.getCategory() != null) {
            response.setCategory(SparePartCategoryResponseDTO.builder()
                    .id(entity.getCategory().getId())
                    .name(entity.getCategory().getName())
                    .description(entity.getCategory().getDescription())
                    .build());
        }

        return response;
    }

    public SparePartEntity toEntityUpdate(SparePartUpdateDTO request, SparePartEntity entity) {
        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        return entity;
    }
}
