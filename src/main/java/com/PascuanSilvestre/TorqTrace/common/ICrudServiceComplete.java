package com.PascuanSilvestre.TorqTrace.common;
import java.util.List;
public interface ICrudServiceComplete<CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
}