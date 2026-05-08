package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopStaffMapper implements IMapper<WorkShopStaffEntity,WorkShopStaffRequestDTO,WorkShopStaffResponseDTO> {
    private final ModelMapper modelMapper;

    @Override
    public WorkShopStaffEntity toEntity(WorkShopStaffRequestDTO request) {
        return  modelMapper.map(request,WorkShopStaffEntity.class);
    }

    @Override
    public WorkShopStaffResponseDTO toResponse(WorkShopStaffEntity entity) {
        return modelMapper.map(entity,WorkShopStaffResponseDTO.class);
    }
}
