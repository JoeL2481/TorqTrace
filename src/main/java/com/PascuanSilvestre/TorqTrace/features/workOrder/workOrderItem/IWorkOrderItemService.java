package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderItem;

import java.util.List;

public interface IWorkOrderItemService<CreateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO update(ID id, CreateDTO request);
    ResponseDTO delete(ID id);
}
