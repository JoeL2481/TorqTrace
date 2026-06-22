package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import com.PascuanSilvestre.TorqTrace.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.IncoherentDataException;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.extraMaintenanceItems.ExtraMaintenanceReminderRepository;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.MaintenanceRepository;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.dto.UserVehicleUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.mapper.UserVehicleMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVehicleService implements IUserVehicleService<UserVehicleCreateDTO, UserVehicleUpdateDTO, UserVehicleResponseDTO, String, UserVehicleEntity> {

    private final UserVehicleRepository repository;
    private final UserVehicleMapper mapper;
    private final VehicleRepository vehicleRepository;
    private final SecurityUtils securityUtils;
    private final MaintenanceRepository maintenanceRepository;
    private final ExtraMaintenanceReminderRepository extraMaintenanceReminderRepository;

    public UserVehicleResponseDTO create(UserVehicleCreateDTO request) {
        validateYear(request.getYear());

        validateUnique(request.getLicencePlate(), request.getVin(), null);

        VehicleEntity particularVehicle = getGenericVehicleById(request.getParticularVehicleId());
        UserVehicleEntity entity = mapper.toEntity(request);

        entity.setUser(securityUtils.getCurrentUser());
        entity.setParticularVehicle(particularVehicle);

        return mapper.toResponse(repository.save(entity));
    }

    private void validateUnique(String licencePlate, String vin, Long currentId) {
        boolean duplicatedPlate;
        if (currentId == null) {
            duplicatedPlate = repository.existsByLicencePlateAndDeletedFalse(licencePlate);
        } else {
            duplicatedPlate = repository.existsByLicencePlateAndIdNotAndDeletedFalse(licencePlate, currentId);
        }

        if (duplicatedPlate) {
            throw new AlreadyExistsException("Licence plate already exists");
        }

        boolean duplicatedVin;
        if (currentId == null) {
            duplicatedVin = repository.existsByVinAndDeletedFalse(vin);
        } else {
            duplicatedVin = repository.existsByVinAndIdNotAndDeletedFalse(vin, currentId);
        }

        if (duplicatedVin) {
            throw new AlreadyExistsException("Vin already exists");
        }
    }

    private VehicleEntity getGenericVehicleById(String publicId) {
        return vehicleRepository.findByPublicId(publicId)
                .orElseThrow(() -> new EntityNotFoundException("Particular vehicle not found for public id: " + publicId));
    }

    public UserVehicleEntity getOwnedVehicleOnly(String publicId) {
        return repository.findByPublicIdAndUserIdAndDeletedFalse(publicId, securityUtils.getCurrentUserId())
                .orElseThrow(() -> new EntityNotFoundException("User vehicle not found for current user"));
    }

    public UserVehicleEntity getAnyVehicleById(String publicId) {
        return repository.findByPublicIdAndDeletedFalse(publicId)
                .orElseThrow(() -> new EntityNotFoundException("User vehicle not found"));
    }

    public void incrementCurrentMileage(UserVehicleEntity userVehicle, int serviceKm) {
        BigDecimal currentKm = userVehicle.getCurrentKm();
        if (currentKm == null) {
            throw new IncoherentDataException("Current vehicle mileage is required");
        }

        if (serviceKm <= 0) {
            throw new IncoherentDataException("Service km must be greater than zero");
        }

        BigDecimal serviceKmValue = BigDecimal.valueOf(serviceKm);

        if (currentKm.compareTo(serviceKmValue) > 0) {
            throw new IncoherentDataException("Service km cannot be lower than current vehicle mileage");
        }

        userVehicle.setCurrentKm(serviceKmValue);
        repository.save(userVehicle);
    }

    public List<UserVehicleResponseDTO> getAll() {
        return repository.findByUserIdAndDeletedFalse(securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UserVehicleResponseDTO getById(String id) {
        return mapper.toResponse(getOwnedVehicleOnly(id));
    }

    public UserVehicleResponseDTO update(String id, UserVehicleUpdateDTO request) {
        UserVehicleEntity entity = getOwnedVehicleOnly(id);

        if (request.getYear() != null) {
            validateYear(request.getYear());
        }

        String licencePlate = entity.getLicencePlate();
        String vin = entity.getVin();

        if (request.getLicencePlate() != null && !request.getLicencePlate().isBlank()) {
            licencePlate = request.getLicencePlate();
        }
        if (request.getVin() != null && !request.getVin().isBlank()) {
            vin = request.getVin();
        }

        validateUnique(licencePlate, vin, entity.getId());

        if (request.getParticularVehicleId() != null) {
            entity.setParticularVehicle(getGenericVehicleById(request.getParticularVehicleId()));
        }

        return mapper.toResponse(repository.save(mapper.toEntityUpdate(request, entity)));

    }

    @Transactional
    public void delete(String id) {
        UserVehicleEntity entity = getOwnedVehicleOnly(id);

        maintenanceRepository.deleteByUserVehicleId(entity.getId());
        extraMaintenanceReminderRepository.deleteByUserVehicleId(entity.getId());
        entity.setDeleted(true);
        entity.setLicencePlate("DELETED-" + entity.getId());
        entity.setVin("DELETED-" + entity.getId());
        repository.save(entity);
    }

    public boolean existByUserVehiclePublicId(String userVehiclePublicId) {
        if (!repository.existsByPublicIdAndDeletedFalse(userVehiclePublicId)) {
            throw new EntityNotFoundException("User vehicle not found");
        }
        return true;
    }

    private void validateYear(Integer year) {
        if (year > Year.now().getValue()) {
            throw new IncoherentDataException("Year cannot be greater than current year");
        }
    }

}
