package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleUpdateDTO;
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
public class UserVehicleController {

    private final UserVehicleService service;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<UserVehicleResponseDTO> create(@Valid @RequestBody UserVehicleCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserVehicleResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserVehicleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserVehicleResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserVehicleUpdateDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
