package com.PascuanSilvestre.TorqTrace.features.auth.userProvider;

import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/userprovider")
@AllArgsConstructor
public class UserProviderController {
    private final UserProviderService userProviderService;

    @PostMapping
    public ResponseEntity<UserProviderResponseDTO> save(@Valid @RequestBody UserProviderCreateDTO request) {
        return ResponseEntity.ok(userProviderService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<UserProviderResponseDTO>>findAll(){
        return ResponseEntity.ok(userProviderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProviderResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(userProviderService.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<UserProviderResponseDTO> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(userProviderService.delete(id));
    }
}
