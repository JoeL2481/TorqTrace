package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import java.util.List;

public interface IWorkShopService<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
    boolean existWorkshop(ID id);
}
