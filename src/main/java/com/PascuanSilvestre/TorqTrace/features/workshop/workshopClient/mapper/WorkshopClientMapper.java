package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.WorkshopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkshopClientMapper implements IMapper<WorkshopClientEntity, WorkshopClientCreateDTO, WorkshopClientResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public WorkshopClientEntity toEntity(WorkshopClientCreateDTO request) {
        return  modelMapper.map(request, WorkshopClientEntity.class);
    }

    @Override
    public WorkshopClientResponseDTO toResponse(WorkshopClientEntity entity) {
        return  modelMapper.map(entity, WorkshopClientResponseDTO.class);
    }

    public void toEntityUpdate (WorkshopClientUpdateDTO request, WorkshopClientEntity entity) {
        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }
}
