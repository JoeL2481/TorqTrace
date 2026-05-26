package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.WorkShopStockEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.dto.WorkShopStockUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopStockMapper implements IMapper<WorkShopStockEntity, WorkShopStockCreateDTO,WorkShopStockResponseDTO> {

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public WorkShopStockEntity toEntity(WorkShopStockCreateDTO request) {
        return  modelMapper.map(request,WorkShopStockEntity.class);
    }

    @Override
    public WorkShopStockResponseDTO toResponse(WorkShopStockEntity entity) {
        return modelMapper.map(entity,WorkShopStockResponseDTO.class);
    }

    public void toEntityUpdate(WorkShopStockUpdateDTO request, WorkShopStockEntity entity) {
        if (request.getMinStockAlert() != null) {
            entity.setMinStockAlert(request.getMinStockAlert());
        }
        if (request.getStockQuantity() != null) {
            entity.setMinStockAlert(request.getMinStockAlert());
        }
    }
}
