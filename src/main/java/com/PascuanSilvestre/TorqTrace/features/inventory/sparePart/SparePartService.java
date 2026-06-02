package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.mapper.SparePartMapper;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryRepository;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityRepository;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityNestedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.mapper.SparePartCompatibilityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartService implements ICrudServiceComplete<SparePartCreateDTO, SparePartUpdateDTO, SparePartResponseDTO, Long> {
    private final SparePartRepository repo;
    private final SparePartCategoryRepository categoryRepo;
    private final SparePartCompatibilityRepository compatibilityRepo;
    private final SparePartMapper mapper;
    private final SparePartCompatibilityMapper compatibilityMapper;

    @Override
    public SparePartResponseDTO create(SparePartCreateDTO request) {
        SparePartEntity entity = mapper.toEntity(request);
        entity.setCategory(getCategoryById(request.getCategoryId()));
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<SparePartResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SparePartResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public SparePartResponseDTO update(Long id, SparePartUpdateDTO request) {
        SparePartEntity entity = getEntityById(id);
        mapper.toEntityUpdate(request, entity);

        if (request.getCategoryId() != null) {
            entity.setCategory(getCategoryById(request.getCategoryId()));
        }

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public SparePartResponseDTO delete(Long id) {
        SparePartEntity entity = getEntityById(id);
        SparePartResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    public SparePartDetailedResponseDTO getDetailedById(Long id) {
        SparePartEntity entity = getEntityById(id);
        List<SparePartCompatibilityNestedResponseDTO> compatibilities = compatibilityRepo.findBySparePartId(id)
                .stream()
                .map(compatibility -> SparePartCompatibilityNestedResponseDTO.builder()
                        .id(compatibility.getId())
                        .vehicleId(compatibility.getVehicle() != null ? compatibility.getVehicle().getId() : null)
                        .engineId(compatibility.getEngine() != null ? compatibility.getEngine().getId() : null)
                        .transmissionId(compatibility.getTransmission() != null ? compatibility.getTransmission().getId() : null)
                        .notes(compatibility.getNotes())
                        .build())
                .toList();

        return SparePartDetailedResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(mapper.toResponse(entity).getCategory())
                .description(entity.getDescription())
                .compatibilities(compatibilities)
                .build();
    }

    private SparePartEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part not found for id: " + id));
    }

    private SparePartCategoryEntity getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part category not found for id: " + id));
    }
}
