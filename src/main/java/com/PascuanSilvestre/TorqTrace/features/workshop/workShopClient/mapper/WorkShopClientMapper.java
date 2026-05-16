package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopClientMapper implements IMapper<WorkShopClientEntity, WorkShopClientCreateDTO, WorkShopClientResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public WorkShopClientEntity toEntity(WorkShopClientCreateDTO request) {
        return  modelMapper.map(request,WorkShopClientEntity.class);
    }

    @Override
    public WorkShopClientResponseDTO toResponse(WorkShopClientEntity entity) {
        return  modelMapper.map(entity,WorkShopClientResponseDTO.class);
    }

    public void toEntityUpdate (WorkShopClientUpdateDTO request, WorkShopClientEntity entity) {
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }
}
