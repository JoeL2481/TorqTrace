package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.mapper;

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
public class WorkshopStockMapper implements IWorkshopStockMapper<WorkshopStockEntity, WorkshopStockCreateDTO, WorkshopStockUpdateDTO, WorkshopStockResponseDTO> {

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public WorkshopStockEntity toEntity(WorkshopStockCreateDTO request) {
        WorkshopStockEntity entity = new WorkshopStockEntity();
        entity.setUnitprice(request.getUnitPrice());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setMinStockAlert(request.getMinStockAlert());
        return entity;
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
            entity.setStockQuantity(request.getStockQuantity());
        }
        if (request.getUnitPrice() != null) {
            entity.setUnitprice(request.getUnitPrice());
        }
    }
}
