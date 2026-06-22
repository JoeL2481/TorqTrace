package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.InvalidRelationshipException;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.mapper.VehicleModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class VehicleModelService implements ICrudService<VehicleModelRequestDTO, VehicleModelResponseDTO,Long>  {
    private final VehicleModelRepository repository;
    private final VehicleModelMapper mapper;
    private final VehicleBrandService vehicleBrandService;
    @Override
    public VehicleModelResponseDTO create(VehicleModelRequestDTO request) {
        if (request.getVehicleBrandId() == null) {
            throw new InvalidRelationshipException("Vehicle brand is required for vehicle model");
        }
        if (repository.existsByNameIgnoreCase(request.getName())) {
            throw new AlreadyExistsException("Vehicle model already exists");
        }

        VehicleModelEntity modelEntity = mapper.toEntity(request);
        modelEntity.setVehicleBrand(vehicleBrandService.getEntityByIdOrName(request.getVehicleBrandId(), null));


        return mapper.toResponse(repository.save(modelEntity));
    }

    @Override
    public List<VehicleModelResponseDTO> getAll() {

        List<VehicleModelResponseDTO> list = repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return list;
    }

    @Override
    public VehicleModelResponseDTO getById(Long id) {


        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(()-> new EntityNotFoundException("Vehicle Model does not exist for id: " + id));
    }

    @Override
    public VehicleModelResponseDTO update(Long id, VehicleModelRequestDTO request) {
        VehicleModelEntity entity = repository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Vehicle Model does not exist for update, id: " + id));
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getVehicleBrandId() != null) {
            entity.setVehicleBrand(vehicleBrandService.getEntityByIdOrName(request.getVehicleBrandId(), null));
        }
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public VehicleModelResponseDTO delete(Long id) {
        VehicleModelEntity entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Vehicle Model was not found for deletion with id: " + id));

        VehicleModelResponseDTO response = mapper.toResponse(entity);
        repository.delete(entity);
        return response;

    }

    public VehicleModelEntity getEntityByIdOrName(Long id, String name) {
        if (id != null) {
            return repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle model not found for id: " + id));
        }

        return repository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle model not found for name: " + name));
    }
}
