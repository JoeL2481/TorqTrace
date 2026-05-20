package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopclient")
@AllArgsConstructor
public class WorkShopClientController {
    private final WorkShopClientService workShopClientService;

    @PostMapping
    public ResponseEntity<WorkShopClientResponseDTO>create(@Valid @RequestBody WorkShopClientCreateDTO workShopClientCreateDTO){
        return ResponseEntity.ok(workShopClientService.create(workShopClientCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<WorkShopClientResponseDTO>>findAll(){
        return ResponseEntity.ok(workShopClientService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkShopClientResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.getById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<WorkShopClientResponseDTO>update(@Valid @RequestBody WorkShopClientUpdateDTO workShopClientUpdateDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.update(id,workShopClientUpdateDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkShopClientResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.delete(id));
    }
}
