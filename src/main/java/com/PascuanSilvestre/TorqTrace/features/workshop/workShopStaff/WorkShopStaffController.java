package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffUpdateDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workshopstaff")
@AllArgsConstructor
public class WorkShopStaffController {
    private final  WorkShopStaffService workShopStaffService;

    @PostMapping
    public ResponseEntity<WorkShopStaffResponseDTO>create(@Valid @RequestBody WorkShopStaffCreateDTO workShopStaffCreateDTO ){
        return ResponseEntity.ok(workShopStaffService.create(workShopStaffCreateDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkShopStaffResponseDTO>get(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStaffService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkShopStaffResponseDTO>>getAll(){
        return ResponseEntity.ok(workShopStaffService.getAll());
    }
    @PostMapping("{id}")
    public ResponseEntity<WorkShopStaffResponseDTO>update(@Valid @RequestBody WorkShopStaffUpdateDTO workShopStaffUpdateDTO, @PathVariable("id") Long id){
        return  ResponseEntity.ok(workShopStaffService.update(id,workShopStaffUpdateDTO));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<WorkShopStaffResponseDTO>delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(workShopStaffService.delete(id));
    }
}
