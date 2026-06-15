package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.mapper.MaintenanceMapper;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleEntity;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.userVehicle.UserVehicleService;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService implements ICrudServiceComplete<MaintenanceCreateDTO, MaintenanceUpdateDTO, MaintenanceDTO, Long> {

    private final MaintenanceRepository repository;
    private final MaintenanceMapper mapper;
    private final UserVehicleService userVehicleService;
    private final WorkOrderRepository workOrderRepository;
    private final SecurityUtils securityUtils;

    public MaintenanceDTO create(Long userVehicleId, MaintenanceCreateDTO request) {
        UserVehicleEntity userVehicle = getAccessibleUserVehicle(userVehicleId);
        WorkOrderEntity workOrder = getWorkOrderById(request.getWorkOrderId());

        MaintenanceEntity entity = mapper.toEntity(request);
        entity.setUserVehicle(userVehicle);
        entity.setWorkshopOrder(workOrder);

        MaintenanceEntity savedEntity = repository.save(entity);
        userVehicleService.incrementCurrentMileage(userVehicle, savedEntity.getServiceKm());

        return mapper.toResponse(savedEntity);
    }

    @Override
    public MaintenanceDTO create(MaintenanceCreateDTO request) {
        throw new UnsupportedOperationException("Use create(userVehicleId, request) for maintenance creation");
    }

    @Override
    public List<MaintenanceDTO> getAll() {
        return repository.findByUserVehicleUserId(securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<MaintenanceDTO> getAllByUserVehicle(Long userVehicleId) {
        userVehicleService.getOwner(userVehicleId);

        return repository.findByUserVehicleIdAndUserVehicleUserId(userVehicleId, securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MaintenanceDTO getById(Long id) {
        return mapper.toResponse(getAccessibleEntity(id));
    }

    @Override
    public MaintenanceDTO update(Long id, MaintenanceUpdateDTO request) {
        MaintenanceEntity entity = getAccessibleEntity(id);

        if (request.getWorkOrderId() != null) {
            entity.setWorkshopOrder(getWorkOrderById(request.getWorkOrderId()));
        }

        mapper.toEntityUpdate(request, entity);
        MaintenanceEntity savedEntity = repository.save(entity);
        userVehicleService.incrementCurrentMileage(savedEntity.getUserVehicle(), savedEntity.getServiceKm());

        return mapper.toResponse(savedEntity);
    }

    @Override
    public MaintenanceDTO delete(Long id) {
        MaintenanceEntity entity = getAccessibleEntity(id);
        MaintenanceDTO response = mapper.toResponse(entity);
        repository.delete(entity);
        return response;
    }

    private MaintenanceEntity getAccessibleEntity(Long id) {
        if (canManageMaintenance()) {
            return repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Maintenance not found"));
        }

        return repository.findByIdAndUserVehicleUserId(id, securityUtils.getCurrentUserId())
                .orElseThrow(() -> new EntityNotFoundException("Maintenance not found for current user"));
    }

    private UserVehicleEntity getAccessibleUserVehicle(Long id) {
        if (canManageMaintenance()) {
            return userVehicleService.getAnyById(id);
        }

        return userVehicleService.getOwner(id);
    }

    private WorkOrderEntity getWorkOrderById(Long id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work order not found"));
    }

    private boolean canManageMaintenance() {
        return securityUtils.hasRole(Roles.ROLE_ADMIN) || securityUtils.hasRole(Roles.ROLE_EMPLOYEE);
    }
}
