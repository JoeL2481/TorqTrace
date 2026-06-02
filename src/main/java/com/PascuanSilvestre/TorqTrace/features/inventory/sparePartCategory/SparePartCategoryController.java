package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/spare-part-category")
@RequiredArgsConstructor
public class SparePartCategoryController {
    private final SparePartCategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SparePartCategoryResponseDTO create(@Valid @RequestBody SparePartCategoryCreateDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public SparePartCategoryResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SparePartCategoryResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<SparePartCategoryResponseDTO> search(@RequestParam String query) {
        return service.search(query);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartCategoryResponseDTO update(@PathVariable Long id, @Valid @RequestBody SparePartCategoryUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartCategoryResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
