package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.CompleteWorkOrderDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.WorkOrderUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workorder")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
@Tag(name = "Work Order", description = "Workshop work order endpoints")
public class WorkOrderController {
    private final IWorkOrderService<WorkOrderCreateDTO, WorkOrderUpdateDTO, WorkOrderResponseDTO, Long> workOrderService;

    @PostMapping
    public ResponseEntity<WorkOrderResponseDTO> create (@Valid @RequestBody WorkOrderCreateDTO workOrderCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(workOrderService.create(workOrderCreateDTO));
    }
    @GetMapping
    public ResponseEntity<List<WorkOrderResponseDTO>> findAll (){
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.getAll());
    }

    @GetMapping("/workshops/{workshopId}")
    public ResponseEntity<List<WorkOrderResponseDTO>> getAllByWorkshop(@PathVariable Long workshopId) {
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.getAllByWorkshop(workshopId));
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.getById(id));
    }

    @GetMapping("/workshops/{workshopId}/{id}")
    public ResponseEntity<WorkOrderResponseDTO> getByIdForWorkshop(@PathVariable Long workshopId, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.getByIdforWorkshop(id, workshopId));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<WorkOrderResponseDTO> complete(@PathVariable Long id, @Valid @RequestBody CompleteWorkOrderDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.completeWorkOrder(id, request));
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO>update(@PathVariable Long id, @Valid @RequestBody WorkOrderUpdateDTO workOrderUpdateDTO){
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.update(id, workOrderUpdateDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WorkOrderResponseDTO> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(workOrderService.delete(id));
    }

}
