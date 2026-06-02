package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.mapper;

import com.PascuanSilvestre.TorqTrace.common.utils.IMapper;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.VehicleVariantEntity;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.vehicleVariant.dto.VehicleVariantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleVariantMapper implements IMapper<VehicleVariantEntity, VehicleVariantRequestDTO, VehicleVariantResponseDTO> {

    private final ModelMapper mapper;

    @Override
    public VehicleVariantEntity toEntity(VehicleVariantRequestDTO request) {
        return mapper.map(request, VehicleVariantEntity.class);
    }

    @Override
    public VehicleVariantResponseDTO toResponse(VehicleVariantEntity entity) {
        return mapper.map(entity, VehicleVariantResponseDTO.class);
    }
}
