package com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.mapper.WorkOrderMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.mapper.WorkShopClientMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.mapper.WorkShopStaffMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.mapper.WorkShopStockMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkShopMapper implements IMapper<WorkShopEntity, WorkShopCreateDTO, WorkShopResponseDTO> {

    private final ModelMapper modelMapper;
    private final WorkShopStaffMapper staffMapper;
    private final WorkShopClientMapper clientMapper;
    private final WorkShopStockMapper stockMapper;
    private final WorkOrderMapper workOrderMapper;
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

    public WorkShopDetailedResponseDTO toDetailedResponse(WorkShopEntity entity) {
        return WorkShopDetailedResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .workshopAddress(entity.getWorkshopAddress())
                .workshopContactInfo(entity.getWorkshopContactInfo())
                .status(entity.isStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())

                .workers(
                        entity.getWorkers()
                                .stream()
                                .map(staffMapper::toResponse).toList()
                )

                .clients(
                        entity.getClients()
                                .stream()
                                .map(clientMapper::toResponse)
                                .toList()
                )

                .stockItems(
                        entity.getStockItems()
                                .stream()
                                .map(stockMapper::toResponse)
                                .toList()
                )

                .orderItems(
                        entity.getOrderItems()
                                .stream()
                                .map(workOrderMapper::toResponse)
                                .toList()
                )

                .build();
    }

}
