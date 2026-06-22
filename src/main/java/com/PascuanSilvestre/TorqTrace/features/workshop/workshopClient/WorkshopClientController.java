package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopclient")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
@Tag(name = "Workshop Client", description = "Workshop client endpoints")
public class WorkshopClientController {
    private final IWorkshopClientService<WorkshopClientCreateDTO, WorkshopClientUpdateDTO, WorkshopClientResponseDTO, Long> workShopClientService;

    @PostMapping
    public ResponseEntity<WorkshopClientResponseDTO> create(@Valid @RequestBody WorkshopClientCreateDTO request) {
        return ResponseEntity.ok(workShopClientService.create(request));
    }

    @GetMapping("/workshops/{workshopId}/clients")
    public ResponseEntity<List<WorkshopClientResponseDTO>>findAllByWorkshop(@PathVariable("workshopId") Long id){
        return ResponseEntity.ok(workShopClientService.getAll(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkshopClientResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkshopClientResponseDTO>update(@Valid @RequestBody WorkshopClientUpdateDTO workShopClientUpdateDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.update(id,workShopClientUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WorkshopClientResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.delete(id));
    }
}
