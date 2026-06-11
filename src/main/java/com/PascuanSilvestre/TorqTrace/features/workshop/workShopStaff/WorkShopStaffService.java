package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.mapper.WorkShopStaffMapper;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkShopStaffService implements IWorkShopStaffService<WorkShopStaffCreateDTO, WorkShopStaffResponseDTO,Long> {
    private final WorkShopStaffRepository workShopStaffRepository;
    private final WorkShopStaffMapper workShopStaffMapper;
    private final SecurityUtils securityUtils;

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

    @Transactional
    public WorkShopStaffResponseDTO update(WorkShopStaffUpdateDTO request) {

        WorkShopStaffEntity employee = workShopStaffRepository.findByUserIdAndWorkshopId(request.getIdEmployee(), request.getIdWorkshop()).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        workShopStaffMapper.toEntityUpdate(request,employee);
        workShopStaffRepository.save(employee);

        if (employee.getRole() == StaffRole.OWNER) {
            throw new IllegalStateException(
                    "The workshop owner role cannot be modified");
        }

        if (request.getRole()== StaffRole.OWNER) {
            throw new IllegalArgumentException(
                    "Owner role cannot be assigned");
        }

        if (employee.getRole() == request.getRole()) {
            throw new IllegalArgumentException(
                    "The employee already has this role");
        }
        employee.setRole(request.getRole());
        workShopStaffRepository.save(employee);

        return workShopStaffMapper.toResponse(employee);
    }

    @Override
    public WorkShopStaffResponseDTO delete(Long id) {
        WorkShopStaffEntity workShopStaff = workShopStaffRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        WorkShopStaffResponseDTO response = workShopStaffMapper.toResponse(workShopStaff);
        workShopStaffRepository.delete(workShopStaff);
        return response;
    }

    public boolean existWorkshopStaff(Long id) {
        if (!workShopStaffRepository.existsById(id)){
            throw new EntityNotFoundException("WorkshopStaff not found");
        }
        return true;
    }

    public boolean existEmployeeWorkshopStaff(Long idEmployee, Long  idWorkshop) {
        if (!workShopStaffRepository.existsByUserIdAndWorkshopId(idEmployee,idWorkshop)){
            throw new EntityNotFoundException("WorkshopStaff not found");
        }
        return true;
    }

    public WorkShopStaffEntity getByEmployeeWorkshopStaff(Long idEmployee, Long  idWorkshop) {
        WorkShopStaffEntity staff = workShopStaffRepository.findByUserIdAndWorkshopId(idEmployee,idWorkshop)
                .orElseThrow(() -> new EntityNotFoundException("workhopStaff was not found for delete"));
        return  staff;
    }

    public WorkShopStaffResponseDTO deleteByEmployeeAndWorkshop(Long idEmployee, Long  idWorkshop) {
        WorkShopStaffEntity workShopStaff = workShopStaffRepository.findByUserIdAndWorkshopId(idEmployee,idWorkshop)
                .orElseThrow(() -> new EntityNotFoundException("workhopStaff was not found for delete"));
        WorkShopStaffResponseDTO response = workShopStaffMapper.toResponse(workShopStaff);
        workShopStaffRepository.delete(workShopStaff);
        return response;
    }


}
