package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder;

import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.dto.CompleteWorkOrderDTO;

import java.util.List;

public interface IWorkOrderService <CreateDTO, UpdateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    List<ResponseDTO> getAllByWorkshop(ID workshopId);
    ResponseDTO getById(ID id);
    ResponseDTO getByIdforWorkshop(ID id, ID workshopId);
    ResponseDTO complete(ID id, CompleteWorkOrderDTO request);
    ResponseDTO update(ID id, UpdateDTO request);
    ResponseDTO delete(ID id);
}
