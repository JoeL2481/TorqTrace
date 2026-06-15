package com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserVehicleService {

    private final UserVehicleRepository repository;
    private final UserVehicleMapper mapper;
    private final VehicleRepository vehicleRepository;
    private final SecurityUtils securityUtils;
    private final MaintenanceRepository maintenanceRepository;
    private final ExtraMaintenanceReminderRepository extraMaintenanceReminderRepository;

    public UserVehicleResponseDTO create(UserVehicleCreateDTO request) {

        validateUnique(request.getLicencePlate(), request.getVin(), null);

        VehicleEntity particularVehicle = getParticularVehicleByPublicId(request.getParticularVehicleId());
        UserVehicleEntity entity = mapper.toEntity(request);

        entity.setUser(securityUtils.getCurrentUser());
        entity.setParticularVehicle(particularVehicle);

        return mapper.toResponse(repository.save(entity));
    }

    private void validateUnique(String licencePlate, String vin, Long currentId) {
        boolean duplicatedPlate;
        if (currentId == null) {
            duplicatedPlate = repository.existsByLicencePlate(licencePlate);
        } else {
            duplicatedPlate = repository.existsByLicencePlateAndIdNot(licencePlate, currentId);
        }

        if (duplicatedPlate) {
            throw new IllegalArgumentException("Licence plate already exists");
        }

        boolean duplicatedVin;
        if (currentId == null) {
            duplicatedVin = repository.existsByVin(vin);
        } else {
            duplicatedVin = repository.existsByVinAndIdNot(vin, currentId);
        }

        if (duplicatedVin) {
            throw new IllegalArgumentException("Vin already exists");
        }
    }

    private VehicleEntity getParticularVehicleByPublicId(UUID publicId) {
        return vehicleRepository.findByPublicId(publicId)
                .orElseThrow(() -> new EntityNotFoundException("Particular vehicle not found for public id: " + publicId));
    }

    public UserVehicleEntity getOwner(Long id) {
        return repository.findByIdAndUserId(id, securityUtils.getCurrentUserId())
                .orElseThrow(() -> new EntityNotFoundException("User vehicle not found for current user"));
    }

    public UserVehicleEntity getAnyById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User vehicle not found"));
    }

    public void incrementCurrentMileage(UserVehicleEntity userVehicle, int serviceKm) {
        if (serviceKm <= 0) {
            return;
        }

        BigDecimal serviceKmValue = BigDecimal.valueOf(serviceKm);
        BigDecimal currentKm = userVehicle.getCurrentKm();

        if (currentKm == null || currentKm.compareTo(serviceKmValue) >= 0) {
            return;
        }

        userVehicle.setCurrentKm(serviceKmValue);
        repository.save(userVehicle);
    }

    public List<UserVehicleResponseDTO> getAll() {
        return repository.findByUserId(securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UserVehicleResponseDTO getById(Long id) {
        return mapper.toResponse(getOwner(id));
    }

    public UserVehicleResponseDTO update(Long id, UserVehicleUpdateDTO request) {
        UserVehicleEntity entity = getOwner(id);

        String licencePlate = entity.getLicencePlate();
        if (request.getLicencePlate() != null && !request.getLicencePlate().isBlank()) {
            licencePlate = request.getLicencePlate();
        }

        String vin = entity.getVin();
        if (request.getVin() != null && !request.getVin().isBlank()) {
            vin = request.getVin();
        }

        validateUnique(licencePlate, vin, id);

        if (request.getParticularVehicleId() != null) {
            entity.setParticularVehicle(getParticularVehicleByPublicId(request.getParticularVehicleId()));
        }

        mapper.toEntityUpdate(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        UserVehicleEntity entity = getOwner(id);

        maintenanceRepository.deleteByUserVehicleId(id);
        extraMaintenanceReminderRepository.deleteByUserVehicleId(id);
        repository.delete(entity);
    }
}
