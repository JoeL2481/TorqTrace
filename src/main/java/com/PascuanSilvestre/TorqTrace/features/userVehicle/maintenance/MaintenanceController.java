package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-vehicle")
@RequiredArgsConstructor
@Tag(name = "Maintenance", description = "Vehicle maintenance history endpoints")
public class MaintenanceController {

    private final MaintenanceService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping("/{userVehicleId}/maintenance")
    public ResponseEntity<MaintenanceDTO> create(@PathVariable String userVehicleId, @Valid @RequestBody MaintenanceCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userVehicleId, request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/maintenance")
    public ResponseEntity<List<MaintenanceDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/{userVehicleId}/maintenance")
    public ResponseEntity<List<MaintenanceDTO>> getAllByUserVehicle(@PathVariable String userVehicleId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getMaintenancesByUserVehicle(userVehicleId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/maintenance/{id}")
    public ResponseEntity<MaintenanceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PutMapping("/maintenance/{id}")
    public ResponseEntity<MaintenanceDTO> update(@PathVariable Long id, @Valid @RequestBody MaintenanceUpdateDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/maintenance/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
