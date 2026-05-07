package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/generations")
@RequiredArgsConstructor
public class VehicleGenerationController {
    private final VehicleGenerationService vehicleGenerationService;


    @PostMapping
    public ResponseEntity<VehicleGenerationResponseDTO> create(@Valid @RequestBody VehicleGenerationRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleGenerationService.create(request));


    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleGenerationResponseDTO> getById(@Valid @PathVariable Long id){

        return ResponseEntity.ok(vehicleGenerationService.getById(id));

    }

    @GetMapping
    public ResponseEntity<List<VehicleGenerationResponseDTO>> getAll(){
        return ResponseEntity.ok(vehicleGenerationService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleGenerationResponseDTO>  delete(@PathVariable Long id){
        vehicleGenerationService.delete(id);
        return ResponseEntity.noContent().build();


    }
    @PutMapping("/{id}")
    public ResponseEntity<VehicleGenerationResponseDTO> update(@PathVariable Long id,  @Valid @RequestBody VehicleGenerationRequestDTO request) {
        return ResponseEntity.ok(vehicleGenerationService.update(id, request));
    }

}
