package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.mapper.SparePartMapper;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.SparePartCategoryRepository;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityRepository;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartService implements ISparePartService<SparePartCreateDTO, SparePartUpdateDTO, SparePartResponseDTO, Long> {
    private final SparePartRepository repo;
    private final SparePartCategoryRepository categoryRepo;
    private final SparePartCompatibilityRepository compatibilityRepo;
    private final SparePartMapper mapper;

    @Override
    public SparePartResponseDTO create(SparePartCreateDTO request) {
        SparePartEntity entity = mapper.toEntity(request);
        entity.setCategory(getCategoryById(request.getCategoryId()));
        return buildResponse(repo.save(entity));
    }

    @Override
    public SparePartResponseDTO getById(Long id) {
        return buildResponse(getEntityById(id));
    }
    @Override
    public List<SparePartResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::buildResponse)
                .toList();
    }



    @Override
    public SparePartResponseDTO update(Long id, SparePartUpdateDTO request) {
        SparePartEntity entity = getEntityById(id);
        entity = mapper.toEntityUpdate(request, entity);

        if (request.getCategoryId() != null) {
            entity.setCategory(getCategoryById(request.getCategoryId()));
        }

        return buildResponse(repo.save(entity));
    }

    @Override
    public void delete(Long id) {
        SparePartEntity entity = getEntityById(id);
        repo.delete(entity);
    }

    public SparePartEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part not found for id: " + id));
    }

    private SparePartCategoryEntity getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part category not found for id: " + id));
    }

    private SparePartResponseDTO buildResponse(SparePartEntity entity) {
        List<SparePartCompatibilityResponseDTO> compatibleList = compatibilityRepo.findBySparePartId(entity.getId())
                .stream()
                .map(compatible -> {
                    Long sparePartId = null;
                    String vehicleId = null;
                    Long engineId = null;
                    Long transmissionId = null;

                    if (compatible.getSparePart() != null) {
                        sparePartId = compatible.getSparePart().getId();
                    }

                    if (compatible.getVehicle() != null) {
                        vehicleId = compatible.getVehicle().getPublicId();
                    }

                    if (compatible.getEngine() != null) {
                        engineId = compatible.getEngine().getId();
                    }

                    if (compatible.getTransmission() != null) {
                        transmissionId = compatible.getTransmission().getId();
                    }

                    return SparePartCompatibilityResponseDTO.builder()
                            .id(compatible.getId())
                            .sparePartId(sparePartId)
                            .vehicleId(vehicleId)
                            .engineId(engineId)
                            .transmissionId(transmissionId)
                            .notes(compatible.getNotes())
                            .build();
                })
                .toList();

        SparePartResponseDTO response = mapper.toResponse(entity);
        response.setCompatibilities(compatibleList);
        return response;
    }

    public boolean existSparePart(Long id) {
        if (!repo.existsById(id)){
            throw new EntityNotFoundException("SparePart not found");
        }
        return true;
    }
}
