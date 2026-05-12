package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopUpdateDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopMapper implements IMapper<WorkShopEntity, WorkShopCreateDTO, WorkShopResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public WorkShopEntity toEntity(WorkShopCreateDTO request) {
        return  modelMapper.map(request, WorkShopEntity.class);
    }

    @Override
    public WorkShopResponseDTO toResponse(WorkShopEntity entity) {
        return  modelMapper.map(entity, WorkShopResponseDTO.class);
    }

    public void toEntityUpdate(WorkShopUpdateDTO request, WorkShopEntity entity) {

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getWorkshopAddress() != null) {
            entity.setWorkshopAddress(request.getWorkshopAddress());
        }

        if (request.getWorkshopContactInfo() != null) {
            entity.setWorkshopContactInfo(request.getWorkshopContactInfo());
        }

        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
    }

}
