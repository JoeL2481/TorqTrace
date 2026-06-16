package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transmission")
@RequiredArgsConstructor
@Tag(name = "Vehicle Powertrain - Transmission", description = "Auxiliary transmission catalog endpoints")
public class TransmissionController {

    private final TransmissionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransmissionResponseDTO create(@Valid @RequestBody TransmissionCreateDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public TransmissionResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransmissionResponseDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransmissionResponseDTO update(@PathVariable Long id, @Valid @RequestBody TransmissionUpdateDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransmissionResponseDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
