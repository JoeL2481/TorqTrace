package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workorder")
@AllArgsConstructor
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    @PostMapping
    public ResponseEntity<WorkOrderResponseDTO> create (@Valid @RequestBody WorkOrderCreateDTO workOrderCreateDTO){
        return ResponseEntity.ok(workOrderService.create(workOrderCreateDTO));
    }
    @GetMapping
    public ResponseEntity<List<WorkOrderResponseDTO>> findAll (){
        return ResponseEntity.ok(workOrderService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(workOrderService.getById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO>update(@PathVariable Long id, @Valid @RequestBody WorkOrderUpdateDTO workOrderUpdateDTO){
        return ResponseEntity.ok(workOrderService.update(id, workOrderUpdateDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(workOrderService.delete(id));
    }

}
