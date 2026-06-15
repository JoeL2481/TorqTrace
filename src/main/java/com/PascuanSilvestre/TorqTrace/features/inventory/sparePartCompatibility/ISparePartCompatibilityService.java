package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCompatibility;

import java.util.List;

public interface ISparePartCompatibilityService<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);

    List<ResponseDTO> getAll();

    ResponseDTO getById(ID id);

    ResponseDTO update(ID id, UpdateDTO request);

    void delete(ID id);
}
