package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.mapper.VehicleBrandMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleBrandService implements ICrudService<VehicleBrandRequestDTO, VehicleBrandResponseDTO, UUID> {


    private final VehicleBrandRepository vehicleBrandRepository;
    private final VehicleBrandMapper vehicleBrandMapper;

    @Override
    public VehicleBrandResponseDTO create(VehicleBrandRequestDTO request) {
        VehicleBrandEntity entity = vehicleBrandMapper.toEntity(request);
        entity.setPublicId(UUID.randomUUID());
        VehicleBrandEntity saved = vehicleBrandRepository.save(entity);
        return vehicleBrandMapper.toResponse(saved);
    }

    @Override
    public List<VehicleBrandResponseDTO> getAll() {
//        return vehicleBrandRepository.findAll()
//                .stream()
//                .map(vehicleBrandMapper::toResponse)
//                .toList();

        List<VehicleBrandEntity> entities = vehicleBrandRepository.findAll();
        List<VehicleBrandResponseDTO> responses = new ArrayList<>();
        for (VehicleBrandEntity entity : entities) {

            responses.add(vehicleBrandMapper.toResponse(entity));
        }
        return responses;
    }

//    @Override
//    public VehicleBrandResponseDTO getById(UUID id) {
//        VehicleBrandEntity entity = vehicleBrandRepository.findByPublicId()
//                .orElseThrow(() -> new EntityNotFoundException("Vehicle brand not found"));
//
//                return vehicleBrandMapper.toResponse(entity);
//
//
//    }

    @Override
    public VehicleBrandResponseDTO getById(UUID id) {
        return vehicleBrandRepository.findByPublicId(id)
                .map(vehicleBrandMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle brand was not found"));

    }

    @Override
    public VehicleBrandResponseDTO update(UUID id, VehicleBrandRequestDTO request) {
       VehicleBrandEntity entity = vehicleBrandRepository.findByPublicId(id)
               .orElseThrow(() -> new EntityNotFoundException("Vehicle brand was not found"));

       entity.setName(request.getName());
       return vehicleBrandMapper.toResponse(vehicleBrandRepository.save(entity));



    }

    @Override
    public void delete(UUID id) {
        vehicleBrandRepository.delete(
                vehicleBrandRepository.findByPublicId(id)
                        .orElseThrow(() -> new EntityNotFoundException("Vehicle brand was not found"))
        );

    }
}
