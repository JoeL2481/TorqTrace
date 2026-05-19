package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopStaffMapper implements IMapper<WorkShopStaffEntity, WorkShopStaffCreateDTO,WorkShopStaffResponseDTO> {
    private final ModelMapper modelMapper;

    @Override
    public WorkShopStaffEntity toEntity(WorkShopStaffCreateDTO request) {
        return  modelMapper.map(request,WorkShopStaffEntity.class);
    }

    @Override
    public WorkShopStaffResponseDTO toResponse(WorkShopStaffEntity entity) {
        return modelMapper.map(entity,WorkShopStaffResponseDTO.class);
    }

    public void toEntityUpdate (WorkShopStaffUpdateDTO request, WorkShopStaffEntity entity) {
        if (request.getRole() != null) {
            entity.setRole(request.getRole());
        }
    }
}
