package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
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


    @Override
    public VehicleGenerationResponseDTO create(VehicleGenerationRequestDTO request) {
        VehicleGenerationEntity entity = vehicleGenerationMapper.toEntity(request);
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

        return vehicleGenerationMapper.toResponse(vehicleGenerationRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        VehicleGenerationEntity entity = vehicleGenerationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vehicle generation not found with id: " + id
                ));

        vehicleGenerationRepository.delete(entity);
    }
}
