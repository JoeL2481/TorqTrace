package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/spare-part-compatibility")
@RequiredArgsConstructor
public class SparePartCompatibilityController {
    private final SparePartCompatibilityService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SparePartCompatibilityResponseDTO create(@Valid @RequestBody SparePartCompatibilityCreateDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public SparePartCompatibilityResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SparePartCompatibilityResponseDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartCompatibilityResponseDTO update(@PathVariable Long id, @Valid @RequestBody SparePartCompatibilityUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartCompatibilityResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
