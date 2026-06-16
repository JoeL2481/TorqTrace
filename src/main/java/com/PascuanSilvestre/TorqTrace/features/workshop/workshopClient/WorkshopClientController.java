package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopclient")
@AllArgsConstructor
@Tag(name = "Workshop Client", description = "Workshop client endpoints")
public class WorkshopClientController {
    private final WorkshopClientService workShopClientService;

    @GetMapping("/workshops/{workshopId}/clients")
    public ResponseEntity<List<WorkshopClientResponseDTO>>findAllByWorkshop(@PathVariable("workshopId") Long id){
        return ResponseEntity.ok(workShopClientService.getAll(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkshopClientResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.getById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<WorkshopClientResponseDTO>update(@Valid @RequestBody WorkshopClientUpdateDTO workShopClientUpdateDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.update(id,workShopClientUpdateDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WorkshopClientResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopClientService.delete(id));
    }
}
