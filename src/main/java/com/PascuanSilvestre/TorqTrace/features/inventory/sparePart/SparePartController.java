package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.dto.SparePartUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/spare-part")
@RequiredArgsConstructor
public class SparePartController {
    private final SparePartService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SparePartResponseDTO create(@Valid @RequestBody SparePartCreateDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public SparePartResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/details")
    public SparePartDetailedResponseDTO getDetailedById(@PathVariable Long id) {
        return service.getDetailedById(id);
    }

    @GetMapping
    public List<SparePartResponseDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartResponseDTO update(@PathVariable Long id, @Valid @RequestBody SparePartUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SparePartResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
