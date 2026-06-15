package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import java.util.List;

public interface IWorkShopStaffService<CreateDTO, ResponseDTO, ID> {
    ResponseDTO create(CreateDTO request);
    List<ResponseDTO> getAll();
    ResponseDTO getById(ID id);
    ResponseDTO delete(ID id);
}
