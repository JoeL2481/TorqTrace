package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.mapper;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SparePartMapper implements ISparePartMapper<SparePartEntity, SparePartCreateDTO, SparePartUpdateDTO, SparePartResponseDTO> {
    private final ModelMapper mapper;

    @Override
    public SparePartEntity toEntity(SparePartCreateDTO request) {
        return mapper.map(request, SparePartEntity.class);
    }

    @Override
    public SparePartResponseDTO toResponse(SparePartEntity entity) {
        return mapper.map(entity, SparePartResponseDTO.class);
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
