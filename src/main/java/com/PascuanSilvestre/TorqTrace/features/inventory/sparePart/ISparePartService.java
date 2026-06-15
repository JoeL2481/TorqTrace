package com.PascuanSilvestre.TorqTrace.features.inventory.sparePart;

import java.util.List;

public interface ISparePartService<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);

    List<ResponseDTO> getAll();

    ResponseDTO getById(ID id);

    ResponseDTO update(ID id, UpdateDTO request);

    void delete(ID id);
}
