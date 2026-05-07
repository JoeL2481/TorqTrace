package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class VehicleBrandController {
    private final VehicleBrandService vehicleBrandService;



    @PostMapping
    public ResponseEntity<VehicleBrandResponseDTO> create(@Valid  @RequestBody VehicleBrandRequestDTO request){

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleBrandService.create(request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VehicleBrandResponseDTO> getById(@Valid @PathVariable UUID id){

        return ResponseEntity.ok(vehicleBrandService.getById(id));

    }

    @GetMapping
    public ResponseEntity<List<VehicleBrandResponseDTO>> getAll(){
        return ResponseEntity.ok(vehicleBrandService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleBrandResponseDTO>  delete(@PathVariable UUID id){
        vehicleBrandService.delete(id);
        return ResponseEntity.noContent().build();


    }
    @PutMapping("/{id}")
    public ResponseEntity<VehicleBrandResponseDTO> update(@PathVariable UUID id,  @Valid @RequestBody VehicleBrandRequestDTO request) {
        return ResponseEntity.ok(vehicleBrandService.update(id, request));
    }

}
