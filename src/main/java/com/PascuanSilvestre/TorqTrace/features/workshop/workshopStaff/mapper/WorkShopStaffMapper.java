package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.mapper;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.WorkshopStaffEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.enums.StaffRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopStaffMapper implements IWorkshopStaffMapper<WorkshopStaffEntity, WorkshopStaffCreateDTO, WorkshopStaffUpdateDTO, WorkshopStaffResponseDTO> {
    private final ModelMapper modelMapper;

    @Override
    public WorkshopStaffEntity toEntity(WorkshopStaffCreateDTO request) {
        WorkshopStaffEntity entity = new WorkshopStaffEntity();

        if (request.getRole() == null) {
            entity.setRole(StaffRole.MECHANIC);
        } else {
            entity.setRole(request.getRole());
        }

        return entity;
    }

    @Override
    public WorkshopStaffResponseDTO toResponse(WorkshopStaffEntity entity) {
        return modelMapper.map(entity, WorkshopStaffResponseDTO.class);
    }

    public void toEntityUpdate (WorkshopStaffUpdateDTO request, WorkshopStaffEntity entity) {
        if (request.getRole() != null) {
            entity.setRole(request.getRole());
        }
    }
}
