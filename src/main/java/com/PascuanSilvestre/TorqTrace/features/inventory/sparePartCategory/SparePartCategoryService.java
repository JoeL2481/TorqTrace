package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.mapper.SparePartCategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartCategoryService implements ISparePartCategoryService<SparePartCategoryCreateDTO, SparePartCategoryUpdateDTO, SparePartCategoryResponseDTO, Long> {
    private final SparePartCategoryRepository repo;
    private final SparePartCategoryMapper mapper;

    @Override
    public SparePartCategoryResponseDTO create(SparePartCategoryCreateDTO request) {
        String simpleName = refactorText(request.getName());
        String description = refactorText(request.getDescription());

        repo.findByNameIgnoreCaseAndDescriptionIgnoreCase(simpleName, description)
                .ifPresent(existing -> {
                    throw new AlreadyExistsException("Spare part category already exists");
                });

        SparePartCategoryEntity entity = mapper.toEntity(request);
        entity.setName(simpleName);
        entity.setDescription(description);

        return mapper.toResponse(repo.save(entity));
    }

    private SparePartCategoryEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part category not found for id: " + id));
    }

    @Override
    public SparePartCategoryResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }
    @Override
    public List<SparePartCategoryResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    @Override
    public SparePartCategoryResponseDTO update(Long id, SparePartCategoryUpdateDTO request) {
        SparePartCategoryEntity entity = getEntityById(id);
        entity = mapper.toEntityUpdate(request, entity);

        if (request.getName() != null) {
            entity.setName(refactorText(request.getName()));
        }

        if (request.getDescription() != null) {
            entity.setDescription(refactorText(request.getDescription()));
        }

        return mapper.toResponse(repo.save(entity));
    }
    private String refactorText(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        String[] words = value.trim().toLowerCase().split("\\s+");
        String result = "";

        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
        }

        return result.trim();
    }

    @Override
    public void delete(Long id) {
        SparePartCategoryEntity entity = getEntityById(id);
        repo.delete(entity);
    }



}
