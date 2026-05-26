package com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.VehicleModelEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleModel.dto.VehicleModelResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.EngineEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehiclePowerTrain.engine.dto.EngineUpdateDTO;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EngineMapper implements IMapper<EngineEntity, EngineCreateDTO, EngineResponseDTO> {
    public final ModelMapper mapper;


    @Override
    public EngineEntity toEntity(EngineCreateDTO request) {
        return mapper.map(request, EngineEntity.class);
    }

    @Override
    public EngineResponseDTO toResponse(EngineEntity engineEntity) {
        return mapper.map(engineEntity, EngineResponseDTO.class);
    }

    public void toEntityUpdate(EngineUpdateDTO request, EngineEntity entity)
        {
            if( request.getCode() != null )
                {
                entity.setCode(request.getCode());
                }

            if(request.getName() != null )
                {
                entity.setName(request.getName());
                }

            if(request.getDisplacementCc() != null)
                {
                entity.setDisplacementCc(request.getDisplacementCc());
                }

            if (request.getPowerHp() != null)
                {
                entity.setPowerHp(request.getPowerHp());
                }

            if (request.getTorqueNm() != null)
                {
                entity.setTorqueNm(request.getTorqueNm());
                }

            if(request.getAmountCylinders() !=null)
                {
                entity.setAmountCylinders(request.getAmountCylinders());
                }

            if(request.getEngineLayout() != null)
                {
                entity.setEngineLayout(request.getEngineLayout());
                }

            if (request.getAspirationType() != null)
                {
                entity.setAspirationType(request.getAspirationType());
                }


        }
}
