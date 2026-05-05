package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.VehicleBrandEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleBrand.dto.VehicleBrandResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleBrandMapper implements IMapper<VehicleBrandEntity, VehicleBrandRequestDTO, VehicleBrandResponseDTO>{


    @Override
    public VehicleBrandEntity toEntity(VehicleBrandRequestDTO dto) {
       return VehicleBrandEntity.builder()
               .name(dto.getName())
               .build();
    }


    @Override
    public VehicleBrandResponseDTO toResponse(VehicleBrandEntity entity) {
        return VehicleBrandResponseDTO.builder()
                .id(entity.getPublicId())
                .name(entity.getName())
                .build();
    }
}



