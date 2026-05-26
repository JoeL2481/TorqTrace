package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.EngineTransmissionEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engineTransmission.dto.EngineTransmissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EngineTransmissionMapper implements IMapper<EngineTransmissionEntity, EngineTransmissionRequestDTO, EngineTransmissionResponseDTO> {

    private final ModelMapper mapper;

    @Override
    public EngineTransmissionEntity toEntity(EngineTransmissionRequestDTO request) {
        return mapper.map(request, EngineTransmissionEntity.class);
    }

    @Override
    public EngineTransmissionResponseDTO toResponse(EngineTransmissionEntity entity) {
        return mapper.map(entity, EngineTransmissionResponseDTO.class);
    }
}
