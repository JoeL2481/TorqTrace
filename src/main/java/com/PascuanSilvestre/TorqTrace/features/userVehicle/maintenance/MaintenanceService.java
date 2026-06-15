package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
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
public class MaintenanceService {

    private final MaintenanceRepository repository;
    private final MaintenanceMapper mapper;
    private final UserVehicleService userVehicleService;
    private final WorkOrderRepository workOrderRepository;
    private final SecurityUtils securityUtils;

    public MaintenanceDTO create(String userVehicleId, MaintenanceCreateDTO request) {
        UserVehicleEntity userVehicle = userVehicleService.getAnyById(userVehicleId);
        WorkOrderEntity workOrder = getWorkOrderById(request.getWorkOrderId());

        MaintenanceEntity maintenance = mapper.toEntity(request);
        maintenance.setUserVehicle(userVehicle);
        maintenance.setWorkshopOrder(workOrder);

        MaintenanceEntity saved = repository.save(maintenance);
        userVehicleService.incrementCurrentMileage(userVehicle, saved.getServiceKm());

        return mapper.toResponse(saved);
    }

    private WorkOrderEntity getWorkOrderById(Long id) {
        return workOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Work order not found"));
    }

    public List<MaintenanceDTO> getAll() {
        if (canEditMaintenance()) {
            return repository.findAll()
                    .stream()
                    .map(mapper::toResponse)
                    .toList();
        }

        return repository.findByUserVehicleUserId(securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<MaintenanceDTO> getMaintenancesByUserVehicle(String userVehicleId) {

        if (canEditMaintenance()) {
            userVehicleService.getAnyById(userVehicleId);

            return repository.findByUserVehiclePublicId(userVehicleId)
                    .stream()
                    .map(mapper::toResponse)
                    .toList();
        }

        userVehicleService.getOwnedVehicle(userVehicleId);

        return repository.findByUserVehiclePublicIdAndUserVehicleUserId(userVehicleId, securityUtils.getCurrentUserId())
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MaintenanceDTO getById(Long id) {
        MaintenanceEntity entity;
        if (canEditMaintenance()) {
            entity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Maintenance not found"));
        } else {
            entity = repository.findByIdAndUserVehicleUserId(id, securityUtils.getCurrentUserId())
                    .orElseThrow(() -> new EntityNotFoundException("Maintenance not found for current user"));
        }

        return mapper.toResponse(entity);
    }

    public MaintenanceDTO update(Long id, MaintenanceUpdateDTO request) {
        MaintenanceEntity maintenance = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance not found"));

        if (request.getWorkOrderId() != null) {
            maintenance.setWorkshopOrder(getWorkOrderById(request.getWorkOrderId()));
        }

        maintenance = mapper.toEntityUpdate(request, maintenance);

        MaintenanceEntity savedEntity = repository.save(maintenance);

        userVehicleService.incrementCurrentMileage(savedEntity.getUserVehicle(), savedEntity.getServiceKm());

        return mapper.toResponse(savedEntity);
    }

    public void delete(Long id) {
        MaintenanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance not found"));

        repository.delete(entity);
    }

    private boolean canEditMaintenance() {
        return securityUtils.hasRole(Roles.ROLE_ADMIN) || securityUtils.hasRole(Roles.ROLE_EMPLOYEE);
    }




}
