package com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.mapper;

import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.MaintenanceEntity;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceDTO;
import com.PascuanSilvestre.TorqTrace.features.userVehicle.maintenance.dto.MaintenanceUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper {

    public MaintenanceEntity toEntity(MaintenanceCreateDTO request) {
        MaintenanceEntity entity = new MaintenanceEntity();
        entity.setMaintenanceType(request.getMaintenanceType());
        entity.setDescription(request.getDescription());
        entity.setServiceKm(request.getServiceKm());
        entity.setNextServiceKm(request.getNextServiceKm());
        entity.setNext_service_date(request.getNextServiceDate());
        return entity;
    }

    public MaintenanceEntity toEntityUpdate(MaintenanceUpdateDTO request, MaintenanceEntity entity) {
        if (request.getMaintenanceType() != null) {
            entity.setMaintenanceType(request.getMaintenanceType());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getServiceKm() != null) {
            entity.setServiceKm(request.getServiceKm());
        }

        if (request.getNextServiceKm() != null) {
            entity.setNextServiceKm(request.getNextServiceKm());
        }

        if (request.getNextServiceDate() != null) {
            entity.setNext_service_date(request.getNextServiceDate());
        }

        return entity;
    }

    public MaintenanceDTO toResponse(MaintenanceEntity entity) {
        MaintenanceDTO response = new MaintenanceDTO();
        response.setId(entity.getId());
        response.setUserVehicleId(entity.getUserVehicle().getPublicId());
        response.setWorkOrderId(entity.getWorkshopOrder().getId());
        response.setMaintenanceType(entity.getMaintenanceType());
        response.setDescription(entity.getDescription());
        response.setServiceKm(entity.getServiceKm());
        response.setNextServiceKm(entity.getNextServiceKm());
        response.setNextServiceDate(entity.getNext_service_date());
        return response;
    }
}
