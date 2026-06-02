package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
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
public class SparePartCategoryService implements ICrudServiceComplete<SparePartCategoryCreateDTO, SparePartCategoryUpdateDTO, SparePartCategoryResponseDTO, Long> {
    private final SparePartCategoryRepository repo;
    private final SparePartCategoryMapper mapper;

    @Override
    public SparePartCategoryResponseDTO create(SparePartCategoryCreateDTO request) {
        String normalizedName = normalizeWords(request.getName());
        String normalizedDescription = normalizeWords(request.getDescription());

        repo.findByNameIgnoreCaseAndDescriptionIgnoreCase(normalizedName, normalizedDescription)
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Spare part category already exists");
                });

        SparePartCategoryEntity entity = mapper.toEntity(request);
        entity.setName(normalizedName);
        entity.setDescription(normalizedDescription);

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<SparePartCategoryResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SparePartCategoryResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public SparePartCategoryResponseDTO update(Long id, SparePartCategoryUpdateDTO request) {
        SparePartCategoryEntity entity = getEntityById(id);
        mapper.toEntityUpdate(request, entity);

        if (request.getName() != null) {
            entity.setName(normalizeWords(request.getName()));
        }

        if (request.getDescription() != null) {
            entity.setDescription(normalizeWords(request.getDescription()));
        }

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public SparePartCategoryResponseDTO delete(Long id) {
        SparePartCategoryEntity entity = getEntityById(id);
        SparePartCategoryResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    private SparePartCategoryEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part category not found for id: " + id));
    }

    public List<SparePartCategoryResponseDTO> search(String query) {
        String normalizedQuery = query.trim().toLowerCase();

        return repo.findAll()
                .stream()
                .filter(category -> {
                    String name = category.getName() == null ? "" : category.getName().toLowerCase();
                    String description = category.getDescription() == null ? "" : category.getDescription().toLowerCase();
                    String combined = (name + " " + description).trim();
                    String reverseCombined = (description + " " + name).trim();

                    return combined.contains(normalizedQuery) || reverseCombined.contains(normalizedQuery);
                })
                .map(mapper::toResponse)
                .toList();
    }

    private String normalizeWords(String value) {
        if (value == null) {
            return null;
        }

        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        String[] words = trimmed.toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1));
        }

        return result.toString();
    }
}
