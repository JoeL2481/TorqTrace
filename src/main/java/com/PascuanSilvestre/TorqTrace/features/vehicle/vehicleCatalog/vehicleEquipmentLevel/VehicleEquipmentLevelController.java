package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle-equipment-level")
@RequiredArgsConstructor
public class VehicleEquipmentLevelController {
    private final VehicleEquipmentLevelService service;

    @PostMapping
    public ResponseEntity<VehicleEquipmentLevelResponseDTO> create(@Valid @RequestBody VehicleEquipmentLevelRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleEquipmentLevelResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleEquipmentLevelResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<VehicleEquipmentLevelResponseDTO> delete(@PathVariable Long id) {

        return ResponseEntity.ok(service.delete(id));


    }


}
