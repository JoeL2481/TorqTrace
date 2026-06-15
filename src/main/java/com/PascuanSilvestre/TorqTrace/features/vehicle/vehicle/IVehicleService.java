package com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle;

import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateCompleteDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicle.dto.VehicleUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleBodyType;
import com.PascuanSilvestre.TorqTrace.features.vehicle.vehicleCatalog.enums.VehicleCategory;

import java.util.List;

public interface IVehicleService<CreateDTO, CompleteCreateDTO, UpdateDTO, ResponseDTO, DetailedResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);

    ResponseDTO createComplete(CompleteCreateDTO request);

    List<ResponseDTO> getAll();

    ResponseDTO getById(ID id);

    DetailedResponseDTO getDetailedById(ID id);

    ResponseDTO update(ID id, UpdateDTO request);

    ResponseDTO delete(ID id);

    List<ResponseDTO> search(
            Long brandId, String brandName,
            Long modelId, String modelName,
            Long variantId, String variantName,
            Long generationId, String generationName,
            Long equipmentLevelId, String equipmentLevelName,
            Long engineId, String engineCode,
            Long transmissionId, String transmissionName,
            VehicleBodyType vehicleBodyType,
            VehicleCategory vehicleCategory
    );
}
