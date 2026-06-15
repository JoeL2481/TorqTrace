package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility;

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
public class SparePartCompatibilityService implements ISparePartCompatibilityService<SparePartCompatibilityCreateDTO, SparePartCompatibilityUpdateDTO, SparePartCompatibilityResponseDTO, Long> {
    private final SparePartCompatibilityRepository repo;
    private final SparePartRepository sparePartRepo;
    private final VehicleRepository vehicleRepo;
    private final EngineRespository engineRepo;
    private final TransmissionRepository transmissionRepo;
    private final SparePartCompatibilityMapper mapper;

    @Override
    public SparePartCompatibilityResponseDTO create(SparePartCompatibilityCreateDTO request) {
        SparePartCompatibilityEntity spare = mapper.toEntity(request);
        addCompatibility(spare, request.getSparePartId(), request.getVehicleId(), request.getEngineId(), request.getTransmissionId());
        return mapper.toResponse(repo.save(spare));
    }

    private SparePartCompatibilityEntity getEntityById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spare part compatibility not found for id: " + id));
    }


    @Override
    public SparePartCompatibilityResponseDTO getById(Long id) {
        return mapper.toResponse(getEntityById(id));
    }

    @Override
    public List<SparePartCompatibilityResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public SparePartCompatibilityResponseDTO update(Long id, SparePartCompatibilityUpdateDTO request) {
        SparePartCompatibilityEntity entity = getEntityById(id);
        entity = mapper.toEntityUpdate(request, entity);
        addCompatibility(entity, request.getSparePartId(), request.getVehicleId(), request.getEngineId(), request.getTransmissionId());
        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public void delete(Long id) {
        SparePartCompatibilityEntity entity = getEntityById(id);
        repo.delete(entity);
    }



    private void addCompatibility(SparePartCompatibilityEntity entity,
                                  Long sparePartId,
                                  String vehicleId,
                                  Long engineId,
                                  Long transmissionId) {
        if (sparePartId != null) {
            SparePartEntity sparePart = sparePartRepo.findById(sparePartId)
                    .orElseThrow(() -> new EntityNotFoundException("Spare part not found for id: " + sparePartId));
            entity.setSparePart(sparePart);
        }

        if (vehicleId != null) {
            VehicleEntity vehicle = vehicleRepo.findByPublicId(vehicleId)
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for public id: " + vehicleId));
            entity.setVehicle(vehicle);
        } else {
            entity.setVehicle(null);
        }

        if (engineId != null) {
            EngineEntity engine = engineRepo.findById(engineId)
                    .orElseThrow(() -> new EntityNotFoundException("Engine not found for id: " + engineId));
            entity.setEngine(engine);
        } else {
            entity.setEngine(null);
        }

        if (transmissionId != null) {
            TransmissionEntity transmission = transmissionRepo.findById(transmissionId)
                    .orElseThrow(() -> new EntityNotFoundException("Transmission not found for id: " + transmissionId));
            entity.setTransmission(transmission);
        } else {
            entity.setTransmission(null);
        }
    }
}
