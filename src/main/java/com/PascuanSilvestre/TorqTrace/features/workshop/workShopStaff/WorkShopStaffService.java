package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.mapper.WorkShopStaffMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkShopStaffService implements ICrudServiceComplete<WorkShopStaffCreateDTO,WorkShopStaffUpdateDTO, WorkShopStaffResponseDTO,Long> {
    private final WorkShopStaffRepository workShopStaffRepository;
    private final WorkShopStaffMapper workShopStaffMapper;
    @Override
    public WorkShopStaffResponseDTO create(WorkShopStaffCreateDTO request) {
        WorkShopStaffEntity workShopStaffEntity = workShopStaffMapper.toEntity(request);
        workShopStaffEntity = workShopStaffRepository.save(workShopStaffEntity);
        return workShopStaffMapper.toResponse(workShopStaffEntity);
    }

    @Override
    public List<WorkShopStaffResponseDTO> getAll() {
        return workShopStaffRepository.findAll().stream().map(workShopStaffMapper::toResponse).toList();
    }

    @Override
    public WorkShopStaffResponseDTO getById(Long id) {
        return workShopStaffRepository.findById(id).
                map(workShopStaffMapper::toResponse).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
    }

    @Override
    public WorkShopStaffResponseDTO update(Long id, WorkShopStaffUpdateDTO request) {
        WorkShopStaffEntity workShopStaff = workShopStaffRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        workShopStaffMapper.toEntityUpdate(request,workShopStaff);
        workShopStaffRepository.save(workShopStaff);
        return workShopStaffMapper.toResponse(workShopStaff);
    }

    @Override
    public WorkShopStaffResponseDTO delete(Long id) {
        WorkShopStaffEntity workShopStaff = workShopStaffRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        WorkShopStaffResponseDTO response = workShopStaffMapper.toResponse(workShopStaff);
        workShopStaffRepository.delete(workShopStaff);
        return response;
    }
}
