package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff;

import java.util.List;

public interface IWorkshopStaffService<CreateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO delete(ID id);
}
