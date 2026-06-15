package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;

import java.util.List;

public interface IWorkshopClientService <CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll(ID id);
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
}