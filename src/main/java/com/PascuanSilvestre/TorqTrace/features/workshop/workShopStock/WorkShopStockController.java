package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopStock")
@AllArgsConstructor
public class WorkShopStockController {

    private final WorkShopStockService workShopStockService;

    @PostMapping
    public ResponseEntity<WorkShopStockResponseDTO>create(@Valid @RequestBody WorkShopStockCreateDTO workShopStockCreateDTO){
        return ResponseEntity.ok(workShopStockService.create(workShopStockCreateDTO));
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<WorkShopStockResponseDTO>get(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.getById(id));
    }

    @GetMapping("/workshops/{workshopId}/stocks")
    public ResponseEntity<List<WorkShopStockResponseDTO>>getAll(@PathVariable("workshopId") Long id){
        return ResponseEntity.ok(workShopStockService.getAll(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkShopStockResponseDTO>update(@Valid @RequestBody WorkShopStockUpdateDTO workShopStockUpdateDTO, @PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.update(id, workShopStockUpdateDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkShopStockResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStockService.delete(id));
    }
}
