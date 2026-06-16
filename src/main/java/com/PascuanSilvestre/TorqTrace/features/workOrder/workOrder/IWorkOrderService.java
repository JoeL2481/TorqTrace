package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import java.util.List;

public interface IWorkOrderService <CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
}
