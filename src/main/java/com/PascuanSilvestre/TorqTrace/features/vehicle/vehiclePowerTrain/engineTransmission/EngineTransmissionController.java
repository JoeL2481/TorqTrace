package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/engine-transmission")
@RequiredArgsConstructor
public class EngineTransmissionController {

    private final EngineTransmissionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EngineTransmissionResponseDTO create(@Valid @RequestBody EngineTransmissionRequestDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public EngineTransmissionResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<EngineTransmissionResponseDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EngineTransmissionResponseDTO update(@PathVariable Long id, @Valid @RequestBody EngineTransmissionRequestDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EngineTransmissionResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
