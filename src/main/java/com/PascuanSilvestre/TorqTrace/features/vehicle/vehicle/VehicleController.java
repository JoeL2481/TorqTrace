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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDTO create(@Valid @RequestBody VehicleCreateDTO request) {
        return service.create(request);
    }

    @PostMapping("/complete")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDTO createComplete(@Valid @RequestBody VehicleCreateCompleteDTO request) {
        return service.createComplete(request);
    }

    @GetMapping("/{id}")
    public VehicleResponseDTO getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/detailed")
    public VehicleDetailedResponseDTO getDetailedById(@PathVariable UUID id) {
        return service.getDetailedById(id);
    }

    @GetMapping
    public List<VehicleResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<VehicleResponseDTO> search(
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
        return service.search(
                brandId, brandName,
                modelId, modelName,
                variantId, variantName,
                generationId, generationName,
                equipmentLevelId, equipmentLevelName,
                engineId, engineCode,
                transmissionId, transmissionName,
                vehicleBodyType, vehicleCategory
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VehicleResponseDTO update(@PathVariable UUID id, @Valid @RequestBody VehicleUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public VehicleResponseDTO delete(@PathVariable UUID id) {
        return service.delete(id);
    }
}
