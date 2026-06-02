package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.mapper.VehicleMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService implements ICrudServiceComplete<VehicleCreateDTO, VehicleUpdateDTO, VehicleResponseDTO, UUID> {

    private final VehicleRepository repo;
    private final VehicleBrandRepository brandRepo;
    private final VehicleModelRepository modelRepo;
    private final VehicleVariantRepository variantRepo;
    private final VehicleGenerationRepository generationRepo;
    private final VehicleEquipmentLevelRepository equipmentLevelRepo;
    private final VehicleMapper mapper;

    @Override
    public VehicleResponseDTO create(VehicleCreateDTO request) {
        VehicleEntity entity = VehicleEntity.builder()
                .publicId(UUID.randomUUID())
                .vehicleBrand(getBrandById(request.getVehicleBrandId()))
                .vehicleModel(getModelById(request.getVehicleModelId()))
                .vehicleVariant(getVariantOrNull(request.getVehicleVariantId()))
                .vehicleGeneration(getGenerationOrNull(request.getVehicleGenerationId()))
                .vehicleEquipmentLevel(getEquipmentLevelOrNull(request.getVehicleEquipmentLevelId()))
                .vehicleBodyType(request.getVehicleBodyType())
                .vehicleCategory(request.getVehicleCategory())
                .build();

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public List<VehicleResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public VehicleResponseDTO getById(UUID id) {
        return mapper.toResponse(getEntityByPublicId(id));
    }

    @Override
    public VehicleResponseDTO update(UUID id, VehicleUpdateDTO request) {
        VehicleEntity entity = getEntityByPublicId(id);

        if (request.getVehicleBrandId() != null) {
            entity.setVehicleBrand(getBrandById(request.getVehicleBrandId()));
        }
        if (request.getVehicleModelId() != null) {
            entity.setVehicleModel(getModelById(request.getVehicleModelId()));
        }
        if (request.getVehicleVariantId() != null) {
            entity.setVehicleVariant(getVariantOrNull(request.getVehicleVariantId()));
        }
        if (request.getVehicleGenerationId() != null) {
            entity.setVehicleGeneration(getGenerationOrNull(request.getVehicleGenerationId()));
        }
        if (request.getVehicleEquipmentLevelId() != null) {
            entity.setVehicleEquipmentLevel(getEquipmentLevelOrNull(request.getVehicleEquipmentLevelId()));
        }
        if (request.getVehicleBodyType() != null) {
            entity.setVehicleBodyType(request.getVehicleBodyType());
        }
        if (request.getVehicleCategory() != null) {
            entity.setVehicleCategory(request.getVehicleCategory());
        }

        return mapper.toResponse(repo.save(entity));
    }

    @Override
    public VehicleResponseDTO delete(UUID id) {
        VehicleEntity entity = getEntityByPublicId(id);
        VehicleResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    public List<VehicleResponseDTO> search(Long brandId, Long modelId, Long variantId, Long generationId,
                                           Long equipmentLevelId, VehicleBodyType vehicleBodyType, VehicleCategory vehicleCategory) {
        return repo.findAll()
                .stream()
                .filter(vehicle -> brandId == null || vehicle.getVehicleBrand().getId().equals(brandId))
                .filter(vehicle -> modelId == null || vehicle.getVehicleModel().getId().equals(modelId))
                .filter(vehicle -> variantId == null || (vehicle.getVehicleVariant() != null && vehicle.getVehicleVariant().getId().equals(variantId)))
                .filter(vehicle -> generationId == null || (vehicle.getVehicleGeneration() != null && vehicle.getVehicleGeneration().getId().equals(generationId)))
                .filter(vehicle -> equipmentLevelId == null || (vehicle.getVehicleEquipmentLevel() != null && vehicle.getVehicleEquipmentLevel().getId().equals(equipmentLevelId)))
                .filter(vehicle -> vehicleBodyType == null || vehicle.getVehicleBodyType() == vehicleBodyType)
                .filter(vehicle -> vehicleCategory == null || vehicle.getVehicleCategory() == vehicleCategory)
                .map(mapper::toResponse)
                .toList();
    }

    private VehicleEntity getEntityByPublicId(UUID id) {
        return repo.findByPublicId(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for public id: " + id));
    }

    private VehicleBrandEntity getBrandById(Long id) {
        return brandRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle brand not found for id: " + id));
    }

    private VehicleModelEntity getModelById(Long id) {
        return modelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle model not found for id: " + id));
    }

    private VehicleVariantEntity getVariantOrNull(Long id) {
        if (id == null) {
            return null;
        }
        return variantRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle variant not found for id: " + id));
    }

    private VehicleGenerationEntity getGenerationOrNull(Long id) {
        if (id == null) {
            return null;
        }
        return generationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle generation not found for id: " + id));
    }

    private VehicleEquipmentLevelEntity getEquipmentLevelOrNull(Long id) {
        if (id == null) {
            return null;
        }
        return equipmentLevelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle equipment level not found for id: " + id));
    }
}
