package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.TransmissionEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.transmission.dto.TransmissionUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransmissionMapper implements IMapper<TransmissionEntity, TransmissionCreateDTO, TransmissionResponseDTO> {

    private final ModelMapper mapper;

    @Override
    public TransmissionEntity toEntity(TransmissionCreateDTO request) {
        return mapper.map(request, TransmissionEntity.class);
    }

    @Override
    public TransmissionResponseDTO toResponse(TransmissionEntity entity) {
        return mapper.map(entity, TransmissionResponseDTO.class);
    }

    public void toEntityUpdate(TransmissionUpdateDTO request, TransmissionEntity entity) {
        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getCode() != null) {
            entity.setCode(request.getCode());
        }

        if (request.getTransmissionType() != null) {
            entity.setTransmissionType(request.getTransmissionType());
        }

        if (request.getGears() != null) {
            entity.setGears(request.getGears());
        }

        if (request.getManufacturer() != null) {
            entity.setManufacturer(request.getManufacturer());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }
    }
}
