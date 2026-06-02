package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.mapper.VehicleVariantMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleVariantService implements ICrudService<VehicleVariantRequestDTO, VehicleVariantResponseDTO, Long> {

    private final VehicleVariantRepository repository;
    private final VehicleVariantMapper mapper;

    @Override
    public VehicleVariantResponseDTO create(VehicleVariantRequestDTO request) {
        VehicleVariantEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<VehicleVariantResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public VehicleVariantResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Variant does not exist for id: " + id));
    }

    @Override
    public VehicleVariantResponseDTO update(Long id, VehicleVariantRequestDTO request) {
        VehicleVariantEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Variant does not exist for update, id: " + id));

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public VehicleVariantResponseDTO delete(Long id) {
        VehicleVariantEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle Variant was not found for deletion with id: " + id));

        VehicleVariantResponseDTO response = mapper.toResponse(entity);
        repository.delete(entity);
        return response;
    }
}
