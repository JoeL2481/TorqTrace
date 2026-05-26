package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.mapper.TransmissionMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransmissionService implements ICrudServiceComplete<TransmissionCreateDTO, TransmissionUpdateDTO, TransmissionResponseDTO, Long> {

    private final TransmissionRepository repo;
    private final TransmissionMapper mapper;

    @Override
    public TransmissionResponseDTO create(TransmissionCreateDTO request) {
        TransmissionEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<TransmissionResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TransmissionEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transmission not found for id: " + id));
    }

    @Override
    public TransmissionResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public TransmissionResponseDTO update(Long id, TransmissionUpdateDTO request) {
        TransmissionEntity entity = getEntityById(id);
        mapper.toEntityUpdate(request, entity);
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public TransmissionResponseDTO delete(Long id) {
        TransmissionEntity entity = getEntityById(id);
        TransmissionResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }
}
