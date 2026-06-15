package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.WorkshopStockEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.dto.WorkshopStockUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkshopStockMapper implements IMapper<WorkshopStockEntity, WorkshopStockCreateDTO, WorkshopStockResponseDTO> {

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public WorkshopStockEntity toEntity(WorkshopStockCreateDTO request) {
        return  modelMapper.map(request, WorkshopStockEntity.class);
    }

    @Override
    public WorkshopStockResponseDTO toResponse(WorkshopStockEntity entity) {
        return modelMapper.map(entity, WorkshopStockResponseDTO.class);
    }

    public void toEntityUpdate(WorkshopStockUpdateDTO request, WorkshopStockEntity entity) {
        if (request.getMinStockAlert() != null) {
            entity.setMinStockAlert(request.getMinStockAlert());
        }
        if (request.getStockQuantity() != null) {
            entity.setMinStockAlert(request.getMinStockAlert());
        }
    }
}
