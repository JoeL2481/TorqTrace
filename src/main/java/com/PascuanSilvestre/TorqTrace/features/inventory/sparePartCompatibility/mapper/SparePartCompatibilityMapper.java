package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.mapper;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class SparePartCompatibilityMapper implements ISparePartCompatibilityMapper<SparePartCompatibilityEntity, SparePartCompatibilityCreateDTO, SparePartCompatibilityUpdateDTO, SparePartCompatibilityResponseDTO> {

    @Override
    public SparePartCompatibilityEntity toEntity(SparePartCompatibilityCreateDTO request) {
        SparePartCompatibilityEntity entity = new SparePartCompatibilityEntity();
        entity.setNotes(request.getNotes());
        return entity;
    }

    @Override
    public SparePartCompatibilityResponseDTO toResponse(SparePartCompatibilityEntity entity) {
        SparePartCompatibilityResponseDTO dto = new SparePartCompatibilityResponseDTO();

        dto.setId(entity.getId());
        dto.setNotes(entity.getNotes());

        if (entity.getSparePart() != null) {
            dto.setSparePartId(entity.getSparePart().getId());
        }
        if (entity.getVehicle() != null) {
            dto.setVehicleId(entity.getVehicle().getPublicId());
        }
        if (entity.getEngine() != null) {
            dto.setEngineId(entity.getEngine().getId());
        }
        if (entity.getTransmission() != null) {
            dto.setTransmissionId(entity.getTransmission().getId());
        }

        return dto;
    }

    public SparePartCompatibilityEntity toEntityUpdate(SparePartCompatibilityUpdateDTO request, SparePartCompatibilityEntity entity) {
        if (request.getNotes() != null) {
            entity.setNotes(request.getNotes());
        }

        return entity;
    }
}
