package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory.dto.SparePartCategoryUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/spare-part-category")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
public class SparePartCategoryController {
    private final ISparePartCategoryService<SparePartCategoryCreateDTO, SparePartCategoryUpdateDTO, SparePartCategoryResponseDTO, Long> service;

    @PostMapping
    public ResponseEntity<SparePartCategoryResponseDTO> create(@Valid @RequestBody SparePartCategoryCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePartCategoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SparePartCategoryResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePartCategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody SparePartCategoryUpdateDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
