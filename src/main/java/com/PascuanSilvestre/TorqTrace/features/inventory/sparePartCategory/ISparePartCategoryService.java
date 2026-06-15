package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import java.util.List;

public interface ISparePartCategoryService<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);

    List<ResponseDTO> getAll();

    ResponseDTO getById(ID id);

    ResponseDTO update(ID id, UpdateDTO request);

    void delete(ID id);
}
