package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicle-variant")
@Tag(name = "Vehicle Catalog - Variant", description = "Auxiliary vehicle variant catalog endpoints")
public class VehicleVariantController {

    private final VehicleVariantService service;

    @PostMapping
    public ResponseEntity<VehicleVariantResponseDTO> create(@Valid @RequestBody VehicleVariantRequestDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleVariantResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleVariantResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleVariantResponseDTO> update(@Positive @PathVariable Long id, @Valid @RequestBody VehicleVariantRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleVariantResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
