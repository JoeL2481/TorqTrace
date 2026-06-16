package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.InvalidRelationshipException;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.dto.VehicleGenerationResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.mapper.VehicleGenerationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleGenerationService implements ICrudService<VehicleGenerationRequestDTO, VehicleGenerationResponseDTO,Long> {

    private final VehicleGenerationRepository vehicleGenerationRepository;
    private final VehicleGenerationMapper vehicleGenerationMapper;
    private final VehicleModelService vehicleModelService;


    @Override
    public VehicleGenerationResponseDTO create(VehicleGenerationRequestDTO request) {
        if (request.getVehicleModelId() == null) {
            throw new InvalidRelationshipException("Vehicle model is required for vehicle generation");
        }
        if (vehicleGenerationRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AlreadyExistsException("Vehicle generation already exists");
        }
        VehicleGenerationEntity entity = vehicleGenerationMapper.toEntity(request);
        entity.setVehicleModel(vehicleModelService.getEntityByIdOrName(request.getVehicleModelId(), null));
        return vehicleGenerationMapper.toResponse
                (vehicleGenerationRepository.save(entity));
    }

    @Override
    public List<VehicleGenerationResponseDTO> getAll() {
        return vehicleGenerationRepository.findAll()
                .stream()
                .map(vehicleGenerationMapper::toResponse)
                .toList();
    }

    @Override
    public VehicleGenerationResponseDTO getById(Long id) {
        return vehicleGenerationRepository.findById(id)
                .map(vehicleGenerationMapper::toResponse)
                .orElseThrow(()-> new EntityNotFoundException("Vehicle Generation does not exist"));
    }

    @Override
    public VehicleGenerationResponseDTO update(Long id, VehicleGenerationRequestDTO request) {
       VehicleGenerationEntity entity = vehicleGenerationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Vehicle Generation does not exist"));
        entity.setName(request.getName());
        entity.setAlias(request.getAlias());
        entity.setYearFrom(request.getYearsFrom());
        entity.setMonthFrom(request.getMonthFrom());
        entity.setYearTo(request.getYearsTo());
        entity.setMonthTo(request.getMonthTo());
        if (request.getVehicleModelId() != null) {
            entity.setVehicleModel(vehicleModelService.getEntityByIdOrName(request.getVehicleModelId(), null));
        }

        return vehicleGenerationMapper.toResponse(vehicleGenerationRepository.save(entity));
    }

    @Override
    public VehicleGenerationResponseDTO delete(Long id) {
        VehicleGenerationEntity entity = vehicleGenerationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vehicle generation not found with id: " + id
                ));
        VehicleGenerationResponseDTO responseDTO = vehicleGenerationMapper.toResponse(entity);
        vehicleGenerationRepository.delete(entity);

        return responseDTO;
    }

    public VehicleGenerationEntity getEntityByIdOrName(Long id, String name) {
        if (id != null) {
            return vehicleGenerationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle generation not found for id: " + id));
        }

        return vehicleGenerationRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle generation not found for name: " + name));
    }
}
