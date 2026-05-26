package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.mapper.EngineMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineService implements ICrudServiceComplete<EngineCreateDTO, EngineUpdateDTO, EngineResponseDTO,Long> {
    public final EngineMapper mapper;
    public final EngineRespository repo;


    @Override
    public EngineResponseDTO create(EngineCreateDTO request) {
        EngineEntity entity = mapper.toEntity(request);


        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<EngineResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public EngineEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engine not found for that id"));
    }

    @Override
    public EngineResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }


    @Override
    public EngineResponseDTO update(Long id, EngineUpdateDTO request) {
        EngineEntity entity = getEntityById(id);

        mapper.toEntityUpdate(request, entity);
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public EngineResponseDTO delete(Long id) {

        EngineEntity entity = getEntityById(id);
        EngineResponseDTO dto = mapper.toResponse(entity);

        repo.delete(entity);

        return dto;
    }
}
