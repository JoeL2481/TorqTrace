package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopstaff")
@AllArgsConstructor
@Tag(name = "Workshop Staff", description = "Workshop staff endpoints")
public class WorkshopStaffController {
    private final WorkshopStaffService workShopStaffService;

    @PostMapping
    public ResponseEntity<WorkshopStaffResponseDTO>create(@Valid @RequestBody WorkshopStaffCreateDTO workShopStaffCreateDTO ){
        return ResponseEntity.ok(workShopStaffService.create(workShopStaffCreateDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkshopStaffResponseDTO>get(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStaffService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkshopStaffResponseDTO>>getAll(){
        return ResponseEntity.ok(workShopStaffService.getAll());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkshopStaffResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStaffService.delete(id));
    }
}
