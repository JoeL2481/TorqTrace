package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopMapper implements IMapper<WorkShopClientEntity, WorkShopRequestDTO, WorkShopResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkShopClientEntity toEntity(WorkShopRequestDTO request) {
        return  modelMapper.map(request, WorkShopClientEntity.class);
    }

    @Override
    public WorkShopResponseDTO toResponse(WorkShopClientEntity entity) {
        return  modelMapper.map(entity, WorkShopResponseDTO.class);
    }
}
