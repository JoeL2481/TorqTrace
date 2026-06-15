package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import java.util.List;

public interface IWorkshopStockService<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll(ID request);
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
}
