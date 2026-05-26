package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineRespository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.mapper.EngineTransmissionMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineTransmissionService implements ICrudService<EngineTransmissionRequestDTO, EngineTransmissionResponseDTO, Long> {

    private final EngineTransmissionRepository repo;
    private final EngineRespository engineRepo;
    private final TransmissionRepository transmissionRepo;
    private final EngineTransmissionMapper mapper;

    @Override
    public EngineTransmissionResponseDTO create(EngineTransmissionRequestDTO request) {
        EngineEntity engine = getEngineById(request.getEngineId());
        TransmissionEntity transmission = getTransmissionById(request.getTransmissionId());

        EngineTransmissionEntity entity = EngineTransmissionEntity.builder()
                .engine(engine)
                .transmission(transmission)
                .build();

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<EngineTransmissionResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EngineTransmissionResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public EngineTransmissionResponseDTO update(Long id, EngineTransmissionRequestDTO request) {
        EngineTransmissionEntity entity = getEntityById(id);

        EngineEntity engine = getEngineById(request.getEngineId());
        TransmissionEntity transmission = getTransmissionById(request.getTransmissionId());

        entity.setEngine(engine);
        entity.setTransmission(transmission);

        return mapper.toResponse(repo.save(entity));
    }



    @Override
    public EngineTransmissionResponseDTO delete(Long id) {
        EngineTransmissionEntity entity = getEntityById(id);
        EngineTransmissionResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    private EngineTransmissionEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engine transmission not found for id: " + id));
    }

    private EngineEntity getEngineById(Long id) {
        return engineRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engine not found for id: " + id));
    }

    private TransmissionEntity getTransmissionById(Long id) {
        return transmissionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transmission not found for id: " + id));
    }
}
