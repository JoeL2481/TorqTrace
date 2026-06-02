package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartEntity;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePart.SparePartRepository;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.dto.SparePartCompatibilityUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.mapper.SparePartCompatibilityMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineRespository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartCompatibilityService implements ICrudServiceComplete<SparePartCompatibilityCreateDTO, SparePartCompatibilityUpdateDTO, SparePartCompatibilityResponseDTO, Long> {
    private final SparePartCompatibilityRepository repo;
    private final SparePartRepository sparePartRepo;
    private final VehicleRepository vehicleRepo;
    private final EngineRespository engineRepo;
    private final TransmissionRepository transmissionRepo;
    private final SparePartCompatibilityMapper mapper;

    @Override
    public SparePartCompatibilityResponseDTO create(SparePartCompatibilityCreateDTO request) {
        SparePartCompatibilityEntity entity = mapper.toEntity(request);
        applyRelations(entity, request.getSparePartId(), request.getVehicleId(), request.getEngineId(), request.getTransmissionId());
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<SparePartCompatibilityResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SparePartCompatibilityResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public SparePartCompatibilityResponseDTO update(Long id, SparePartCompatibilityUpdateDTO request) {
        SparePartCompatibilityEntity entity = getEntityById(id);
        mapper.toEntityUpdate(request, entity);
        applyRelations(entity, request.getSparePartId(), request.getVehicleId(), request.getEngineId(), request.getTransmissionId());
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public SparePartCompatibilityResponseDTO delete(Long id) {
        SparePartCompatibilityEntity entity = getEntityById(id);
        SparePartCompatibilityResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    private SparePartCompatibilityEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part compatibility not found for id: " + id));
    }

    private void applyRelations(SparePartCompatibilityEntity entity, Long sparePartId, Long vehicleId, Long engineId, Long transmissionId) {
        if (sparePartId != null) {
            entity.setSparePart(getSparePartById(sparePartId));
        }

        if (vehicleId != null) {
            entity.setVehicle(getVehicleById(vehicleId));
        } else {
            entity.setVehicle(null);
        }

        if (engineId != null) {
            entity.setEngine(getEngineById(engineId));
        } else {
            entity.setEngine(null);
        }

        if (transmissionId != null) {
            entity.setTransmission(getTransmissionById(transmissionId));
        } else {
            entity.setTransmission(null);
        }
    }

    private SparePartEntity getSparePartById(Long id) {
        return sparePartRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part not found for id: " + id));
    }

    private VehicleEntity getVehicleById(Long id) {
        return vehicleRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id: " + id));
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
