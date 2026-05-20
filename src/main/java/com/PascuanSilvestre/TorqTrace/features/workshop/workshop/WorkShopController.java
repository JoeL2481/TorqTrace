package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;


import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshop")
@RequiredArgsConstructor
public class WorkShopController {

    private final WorkShopService service;

    @PostMapping
    public ResponseEntity<WorkShopResponseDTO> create(@Valid @RequestBody WorkShopCreateDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkShopResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkShopResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PutMapping ("/{id}")
    public ResponseEntity<WorkShopResponseDTO> update(@PathVariable Long id, @Valid @RequestBody WorkShopUpdateDTO request) {
        return  ResponseEntity.ok(service.update(id,request));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<WorkShopResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
