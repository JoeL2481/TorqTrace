package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicle-model")

public class VehicleModelController {

    private final VehicleModelService service;

    @PostMapping
    public ResponseEntity<VehicleModelResponseDTO> create(@Valid @RequestBody VehicleModelRequestDTO request) {
        return  ResponseEntity.ok(service.create(request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VehicleModelResponseDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));

    }
    @GetMapping
    public ResponseEntity<List<VehicleModelResponseDTO>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleModelResponseDTO> update(@Positive @PathVariable Long id, @Valid @RequestBody VehicleModelRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleModelResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));

    }
}
