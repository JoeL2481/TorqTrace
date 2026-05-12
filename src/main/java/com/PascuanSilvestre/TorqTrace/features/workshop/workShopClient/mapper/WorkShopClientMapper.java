package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopClientMapper implements IMapper<WorkShopClientEntity,WorkShopClientRequestDTO, WorkShopClientResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public WorkShopClientEntity toEntity(WorkShopClientRequestDTO request) {
        return  modelMapper.map(request,WorkShopClientEntity.class);
    }

    @Override
    public WorkShopClientResponseDTO toResponse(WorkShopClientEntity entity) {
        return  modelMapper.map(entity,WorkShopClientResponseDTO.class);
    }
}
