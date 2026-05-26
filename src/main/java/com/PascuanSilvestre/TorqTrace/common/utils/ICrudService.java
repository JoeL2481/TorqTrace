package com.PascuanSilvestre.TorqTrace.common.utils;

import java.util.List;

public interface ICrudService<RequestDTO, ResponseDTO, ID> {
    ResponseDTO create(RequestDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, RequestDTO request);
    ResponseDTO delete(ID id);
}