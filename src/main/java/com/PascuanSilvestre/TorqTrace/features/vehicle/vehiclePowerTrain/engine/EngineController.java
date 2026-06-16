package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/engine")
@RequiredArgsConstructor
@Tag(name = "Vehicle Powertrain - Engine", description = "Auxiliary engine catalog endpoints")
public class EngineController {

    private final EngineService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EngineResponseDTO create(@Valid @RequestBody EngineCreateDTO request) {
        return service.create(request);
    }


    @GetMapping("/{id}")
    public EngineResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EngineResponseDTO> getAll(@PathVariable Long id) {
        return service.getAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EngineResponseDTO update(@PathVariable Long id, @Valid @RequestBody EngineUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EngineResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
