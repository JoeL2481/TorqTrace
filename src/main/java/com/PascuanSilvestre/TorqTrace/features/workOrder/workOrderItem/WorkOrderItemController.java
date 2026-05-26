package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem.dto.WorkOrderItemResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workorderitem")
@AllArgsConstructor
public class WorkOrderItemController {
    private final WorkOrderItemService workOrderItemService;

    @PostMapping
    public ResponseEntity<WorkOrderItemResponseDTO> create (@Valid @RequestBody WorkOrderItemCreateDTO workOrderItemCreateDTO){
        return ResponseEntity.ok(workOrderItemService.create(workOrderItemCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderItemResponseDTO>> findAll(){
        return ResponseEntity.ok(workOrderItemService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<WorkOrderItemResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(workOrderItemService.getById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkOrderItemResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workOrderItemService.delete(id));
    }
}
