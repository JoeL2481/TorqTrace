package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SparePartCompatibilityMapper implements IMapper<SparePartCompatibilityEntity, SparePartCompatibilityCreateDTO, SparePartCompatibilityResponseDTO> {
    private final ModelMapper mapper;

    @Override
    public SparePartCompatibilityEntity toEntity(SparePartCompatibilityCreateDTO request) {
        return mapper.map(request, SparePartCompatibilityEntity.class);
    }

    @Override
    public SparePartCompatibilityResponseDTO toResponse(SparePartCompatibilityEntity entity) {
        SparePartCompatibilityResponseDTO dto = mapper.map(entity, SparePartCompatibilityResponseDTO.class);

        if (entity.getSparePart() != null) {
            dto.setSparePartId(entity.getSparePart().getId());
        }
        if (entity.getVehicle() != null) {
            dto.setVehicleId(entity.getVehicle().getId());
        }
        if (entity.getEngine() != null) {
            dto.setEngineId(entity.getEngine().getId());
        }
        if (entity.getTransmission() != null) {
            dto.setTransmissionId(entity.getTransmission().getId());
        }

        return dto;
    }

    public void toEntityUpdate(SparePartCompatibilityUpdateDTO request, SparePartCompatibilityEntity entity) {
        if (request.getNotes() != null) {
            entity.setNotes(request.getNotes());
        }
    }
}
