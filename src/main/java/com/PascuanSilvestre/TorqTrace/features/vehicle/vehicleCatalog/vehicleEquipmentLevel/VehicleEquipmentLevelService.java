package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.dto.VehicleEquipmentLevelResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.mapper.VehicleEquipmentLevelMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleEquipmentLevelService {
    private final VehicleEquipmentLevelRepository repository;
    private final VehicleEquipmentLevelMapper mapper;


    public VehicleEquipmentLevelResponseDTO create(VehicleEquipmentLevelRequestDTO request) {
        VehicleEquipmentLevelEntity entity = mapper.toEntity(request);

        return mapper.toResponse(repository.save(entity));
    }

    public List<VehicleEquipmentLevelResponseDTO> getAll()
    {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    public VehicleEquipmentLevelResponseDTO getById(Long id)
    {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow( ()-> new EntityNotFoundException("Equipment level not found for that id"));

    }

    public VehicleEquipmentLevelResponseDTO update(Long id,VehicleEquipmentLevelRequestDTO request)
    {
        VehicleEquipmentLevelEntity entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Equipment level does not exist for update"));
        entity.setName(request.getName());
        return mapper.toResponse(repository.save(entity));
    }

    public VehicleEquipmentLevelResponseDTO  delete(Long id)
    {

        VehicleEquipmentLevelEntity entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Equipment level not found for deletion"));
        VehicleEquipmentLevelResponseDTO response = mapper.toResponse(entity);
        repository.delete(entity);

        return  response;
    }
}
