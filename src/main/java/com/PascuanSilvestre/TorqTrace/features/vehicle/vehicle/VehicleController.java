package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateCompleteDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> create(@Valid @RequestBody VehicleCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PostMapping("/complete")
    public ResponseEntity<VehicleResponseDTO> createComplete(@Valid @RequestBody VehicleCreateCompleteDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComplete(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping("/{id}/detailed")
    public ResponseEntity<VehicleDetailedResponseDTO> getDetailedById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getDetailedById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }


    @GetMapping("/search")
    public ResponseEntity<List<VehicleResponseDTO>> search(
            @RequestParam(required = false) Long brandId, @RequestParam(required = false) String brandName,
            @RequestParam(required = false) Long modelId, @RequestParam(required = false) String modelName,
            @RequestParam(required = false) Long variantId, @RequestParam(required = false) String variantName,
            @RequestParam(required = false) Long generationId, @RequestParam(required = false) String generationName,
            @RequestParam(required = false) Long equipmentLevelId, @RequestParam(required = false) String equipmentLevelName,
            @RequestParam(required = false) Long engineId, @RequestParam(required = false) String engineCode,
            @RequestParam(required = false) Long transmissionId, @RequestParam(required = false) String transmissionName,
            @RequestParam(required = false) VehicleBodyType vehicleBodyType,
            @RequestParam(required = false) VehicleCategory vehicleCategory
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(
                brandId, brandName,
                modelId, modelName,
                variantId, variantName,
                generationId, generationName,
                equipmentLevelId, equipmentLevelName,
                engineId, engineCode,
                transmissionId, transmissionName,
                vehicleBodyType, vehicleCategory
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> update(@PathVariable String id, @Valid @RequestBody VehicleUpdateDTO request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(id));
    }
}
