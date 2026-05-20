package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType.dto.WorkOrderTypeResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workordertype")
@AllArgsConstructor
public class WorkOrderTypeController {
    private WorkOrderTypeService workOrderTypeService;

    @PostMapping
    public ResponseEntity<WorkOrderTypeResponseDTO>create(@Valid @RequestBody WorkOrderTypeCreateDTO workOrderTypeCreateDTO){
        return ResponseEntity.ok(workOrderTypeService.create(workOrderTypeCreateDTO));
    }
    @GetMapping
    public ResponseEntity<List<WorkOrderTypeResponseDTO>> getAll(){
        return ResponseEntity.ok(workOrderTypeService.getAll());
    }
    @GetMapping("{id}")
    public ResponseEntity<WorkOrderTypeResponseDTO> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(workOrderTypeService.getById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkOrderTypeResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workOrderTypeService.delete(id));
    }
}
