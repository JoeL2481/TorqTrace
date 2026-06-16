package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopStock")
@AllArgsConstructor
public class WorkshopStockController {

    private final WorkshopStockService workShopStockService;

    @PostMapping
    public ResponseEntity<WorkshopStockResponseDTO>create(@Valid @RequestBody WorkshopStockCreateDTO workShopStockCreateDTO){
        return ResponseEntity.ok(workShopStockService.create(workShopStockCreateDTO));
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<WorkshopStockResponseDTO>get(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.getById(id));
    }

    @GetMapping("/workshops/{workshopId}/stocks")
    public ResponseEntity<List<WorkshopStockResponseDTO>>getAll(@PathVariable("workshopId") Long id){
        return ResponseEntity.ok(workShopStockService.getAll(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkshopStockResponseDTO>update(@Valid @RequestBody WorkshopStockUpdateDTO workShopStockUpdateDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.update(id, workShopStockUpdateDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkshopStockResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.delete(id));
    }
}
