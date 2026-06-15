package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getMyProfile() {
        return ResponseEntity.ok(service.getMyProfile());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile/details")
    public ResponseEntity<UserDetailedResponseDTO> getMyProfileDetails() {
        return ResponseEntity.ok(service.getMyProfileDetails());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateMyProfile(@Valid @RequestBody UserUpdateDTO request) {
        return ResponseEntity.ok(service.updateMyProfile(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/details")
    public ResponseEntity<UserDetailedResponseDTO> getDetailedById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getDetailedByID(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
