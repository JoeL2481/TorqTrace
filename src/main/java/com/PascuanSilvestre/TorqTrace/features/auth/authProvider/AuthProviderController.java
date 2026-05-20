package com.PascuanSilvestre.TorqTrace.features.auth.authProvider;

import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/authprovider")
@AllArgsConstructor

public class AuthProviderController {

     private final AuthProviderService  authProviderService;

     @PostMapping
    public ResponseEntity<AuthProviderResponseDTO> create(@Valid @RequestBody AuthProviderCreateDTO request) {
         return ResponseEntity.ok(authProviderService.create(request));
     }
     @GetMapping
    public ResponseEntity<List<AuthProviderResponseDTO>> findAll() {
         return ResponseEntity.ok(authProviderService.getAll());
     }
     @GetMapping("/{id}")
    public ResponseEntity<AuthProviderResponseDTO> findById(@Valid @PathVariable("id") Long id) {
         return ResponseEntity.ok(authProviderService.getById(id));
     }
     @DeleteMapping("/{id}")
    public ResponseEntity<AuthProviderResponseDTO> delete(@Valid @PathVariable("id") Long id) {
         return ResponseEntity.ok(authProviderService.delete(id));
     }

}
