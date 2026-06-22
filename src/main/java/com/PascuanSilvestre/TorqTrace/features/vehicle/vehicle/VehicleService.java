package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.DeletionAttemptException;
import com.PascuanSilvestre.TorqTrace.common.utils.ValidationDTO;
import com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility.SparePartCompatibilityRepository;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleRepository;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateCompleteDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.mapper.VehicleMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleEquipmentLevel.VehicleEquipmentLevelService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleGeneration.VehicleGenerationService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineService;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService<VehicleCreateDTO, VehicleCreateCompleteDTO, VehicleUpdateDTO, VehicleResponseDTO, VehicleDetailedResponseDTO, String> {

    private final VehicleRepository repo;
    private final VehicleBrandService brandService;
    private final VehicleModelService modelService;
    private final VehicleVariantService variantService;
    private final VehicleGenerationService generationService;
    private final VehicleEquipmentLevelService equipmentLevelService;
    private final EngineService engineService;
    private final TransmissionService transmissionService;
    private final UserVehicleRepository userVehicleRepository;
    private final SparePartCompatibilityRepository sparePartCompatibilityRepository;
    private final VehicleMapper mapper;

    @Override
    public VehicleResponseDTO create(VehicleCreateDTO request) {

        VehicleEntity entity = mapper.toEntity(request);
        entity.setVehicleBrand(brandService.getEntityByIdOrName(request.getVehicleBrandId(), request.getVehicleBrandName()));
        entity.setVehicleModel(modelService.getEntityByIdOrName(request.getVehicleModelId(), request.getVehicleModelName()));
        validateUniqueConfiguration(entity);

        return mapper.toResponse(repo.save(entity));
    }

    public VehicleResponseDTO createComplete(VehicleCreateCompleteDTO request) {
        VehicleEntity entity = mapper.toCompleteEntity(request);
        entity.setVehicleBrand(brandService.getEntityByIdOrName(request.getVehicleBrandId(), request.getVehicleBrandName()));
        entity.setVehicleModel(modelService.getEntityByIdOrName(request.getVehicleModelId(), request.getVehicleModelName()));
        entity.setVehicleGeneration(generationService.getEntityByIdOrName(request.getVehicleGenerationId(), request.getVehicleGenerationName()));
        entity.setVehicleVariant(variantService.getEntityByIdOrName(request.getVehicleVariantId(), request.getVehicleVariantName()));
        entity.setVehicleEquipmentLevel(equipmentLevelService.getEntityByIdOrName(request.getVehicleEquipmentLevelId(), request.getVehicleEquipmentLevelName()));
        entity.setEngine(engineService.getEntityByIdOrName(request.getEngineId(), request.getEngineCode()));
        entity.setTransmission(transmissionService.getEntityByIdOrName(request.getTransmissionId(), request.getTransmissionName()));

        validateUniqueConfiguration(entity);

        return mapper.toResponse(repo.save(entity));
    }

    private void validateUniqueConfiguration(VehicleEntity entity) {
        boolean exists = repo.existsDuplicateConfiguration(
                entity.getVehicleBrand().getId(),
                entity.getVehicleModel().getId(),
                entity.getVehicleGeneration() == null ? null : entity.getVehicleGeneration().getId(),
                entity.getVehicleVariant() == null ? null : entity.getVehicleVariant().getId(),
                entity.getVehicleEquipmentLevel() == null ? null : entity.getVehicleEquipmentLevel().getId(),
                entity.getEngine() == null ? null : entity.getEngine().getId(),
                entity.getTransmission() == null ? null : entity.getTransmission().getId(),
                entity.getVehicleBodyType(),
                entity.getVehicleCategory()
        );

        if (exists) {
            throw new AlreadyExistsException("Vehicle already exists with the same configuration");
        }
    }

    private VehicleEntity getEntityByPublicId(String id) {
        return repo.findByPublicId(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for public id: " + id));
    }


    @Override
    public VehicleResponseDTO getById(String id) {

        return mapper.toResponse(getEntityByPublicId(id));
    }

    public VehicleDetailedResponseDTO getDetailedById(String id) {
        return mapper.toDetailedResponse(getEntityByPublicId(id));
    }

    @Override
    public List<VehicleResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public VehicleResponseDTO update(String id, VehicleUpdateDTO request) {
        VehicleEntity entity = getEntityByPublicId(id);

        Long brandId = request.getVehicleBrandId();
        String brandName = request.getVehicleBrandName();

        if (ValidationDTO.isPresent(brandId) || ValidationDTO.isPresent(brandName)) {
            entity.setVehicleBrand(brandService.getEntityByIdOrName(brandId, brandName));
        }

        Long modelId = request.getVehicleModelId();
        String modelName = request.getVehicleModelName();

        if (ValidationDTO.isPresent(modelId) || ValidationDTO.isPresent(modelName)) {
            entity.setVehicleModel(modelService.getEntityByIdOrName(modelId, modelName));
        }

        Long variantId = request.getVehicleVariantId();
        String variantName = request.getVehicleVariantName();

        if (ValidationDTO.isPresent(variantId) || ValidationDTO.isPresent(variantName)) {
            entity.setVehicleVariant(variantService.getEntityByIdOrName(variantId, variantName));
        }

        Long generationId = request.getVehicleGenerationId();
        String generationName = request.getVehicleGenerationName();

        if (ValidationDTO.isPresent(generationId) || ValidationDTO.isPresent(generationName)) {
            entity.setVehicleGeneration(generationService.getEntityByIdOrName(generationId, generationName));
        }

        Long equipmentLevelId = request.getVehicleEquipmentLevelId();
        String equipmentLevelName = request.getVehicleEquipmentLevelName();

        if (ValidationDTO.isPresent(equipmentLevelId) || ValidationDTO.isPresent(equipmentLevelName)) {
            entity.setVehicleEquipmentLevel(equipmentLevelService.getEntityByIdOrName(equipmentLevelId, equipmentLevelName));
        }

        Long engineId = request.getEngineId();
        String engineCode = request.getEngineCode();

        if (ValidationDTO.isPresent(engineId) || ValidationDTO.isPresent(engineCode)) {
            entity.setEngine(engineService.getEntityByIdOrName(engineId, engineCode));
        }

        Long transmissionId = request.getTransmissionId();
        String transmissionName = request.getTransmissionName();

        if (ValidationDTO.isPresent(transmissionId) || ValidationDTO.isPresent(transmissionName)) {
            entity.setTransmission(transmissionService.getEntityByIdOrName(transmissionId, transmissionName));
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
    public VehicleResponseDTO delete(String id) {
        VehicleEntity entity = getEntityByPublicId(id);

        validateDeletionAllowed(entity);

        VehicleResponseDTO dto = mapper.toResponse(entity);
        repo.delete(entity);
        return dto;
    }

    private void validateDeletionAllowed(VehicleEntity entity) {
        if (userVehicleRepository.existsByParticularVehicleId(entity.getId())) {
            throw new DeletionAttemptException("Vehicle cannot be deleted because it is assigned to a user vehicle");
        }

        if (sparePartCompatibilityRepository.existsByVehicleId(entity.getId())) {
            throw new DeletionAttemptException("Vehicle cannot be deleted because it is used in spare part compatibility");
        }
    }

    public List<VehicleResponseDTO> search(
            Long brandId, String brandName,
            Long modelId, String modelName,
            Long variantId, String variantName,
            Long generationId, String generationName,
            Long equipmentLevelId, String equipmentLevelName,
            Long engineId, String engineCode,
            Long transmissionId, String transmissionName,
            VehicleBodyType vehicleBodyType,
            VehicleCategory vehicleCategory
    ) {
        final VehicleBrandEntity brand =
                (ValidationDTO.isPresent(brandId) || ValidationDTO.isPresent(brandName))
                        ? brandService.getEntityByIdOrName(brandId, brandName) : null;

        final VehicleModelEntity model =
                (ValidationDTO.isPresent(modelId) || ValidationDTO.isPresent(modelName))
                        ? modelService.getEntityByIdOrName(modelId, modelName) : null;

        final VehicleVariantEntity variant =
                (ValidationDTO.isPresent(variantId) || ValidationDTO.isPresent(variantName))
                        ? variantService.getEntityByIdOrName(variantId, variantName) : null;

        final VehicleGenerationEntity generation =
                (ValidationDTO.isPresent(generationId) || ValidationDTO.isPresent(generationName))
                        ? generationService.getEntityByIdOrName(generationId, generationName) : null;

        final VehicleEquipmentLevelEntity equipmentLevel =
                (ValidationDTO.isPresent(equipmentLevelId) || ValidationDTO.isPresent(equipmentLevelName))
                        ? equipmentLevelService.getEntityByIdOrName(equipmentLevelId, equipmentLevelName) : null;

        final EngineEntity engine = (ValidationDTO.isPresent(engineId) || ValidationDTO.isPresent(engineCode)) ? engineService.getEntityByIdOrName(engineId, engineCode) : null;
        final TransmissionEntity transmission = (ValidationDTO.isPresent(transmissionId) || ValidationDTO.isPresent(transmissionName)) ? transmissionService.getEntityByIdOrName(transmissionId,transmissionName) : null;

        return repo.findAll()
                .stream()
                .filter(vehicle -> brand == null || vehicle.getVehicleBrand().getId().equals(brand.getId()))
                .filter(vehicle -> model == null || vehicle.getVehicleModel().getId().equals(model.getId()))
                .filter(vehicle -> variant == null || (vehicle.getVehicleVariant() != null && vehicle.getVehicleVariant().getId().equals(variant.getId())))
                .filter(vehicle -> generation == null || (vehicle.getVehicleGeneration() != null && vehicle.getVehicleGeneration().getId().equals(generation.getId())))
                .filter(vehicle -> equipmentLevel == null || (vehicle.getVehicleEquipmentLevel() != null && vehicle.getVehicleEquipmentLevel().getId().equals(equipmentLevel.getId())))
                .filter(vehicle -> engine == null || (vehicle.getEngine() != null && vehicle.getEngine().getId().equals(engine.getId())))
                .filter(vehicle -> transmission == null || (vehicle.getTransmission() != null && vehicle.getTransmission().getId().equals(transmission.getId())))
                .filter(vehicle -> vehicleBodyType == null || vehicle.getVehicleBodyType() == vehicleBodyType)
                .filter(vehicle -> vehicleCategory == null || vehicle.getVehicleCategory() == vehicleCategory)
                .map(mapper::toResponse)
                .toList();
    }


}
