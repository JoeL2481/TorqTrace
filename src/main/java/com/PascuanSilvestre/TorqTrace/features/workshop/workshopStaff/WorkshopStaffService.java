package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff;

import com.PascuanSilvestre.TorqTrace.common.exception.IncoherentDataException;
import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto.WorkshopStaffUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.mapper.WorkShopStaffMapper;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkshopStaffService implements IWorkshopStaffService<WorkshopStaffCreateDTO, WorkshopStaffResponseDTO,Long> {
    private final WorkshopStaffRepository workShopStaffRepository;
    private final WorkShopStaffMapper workShopStaffMapper;
    private final SecurityUtils securityUtils;

    @Override
    public WorkshopStaffResponseDTO create(WorkshopStaffCreateDTO request) {
        WorkshopStaffEntity workShopStaffEntity = workShopStaffMapper.toEntity(request);
        workShopStaffEntity = workShopStaffRepository.save(workShopStaffEntity);
        return workShopStaffMapper.toResponse(workShopStaffEntity);
    }

    @Override
    public List<WorkshopStaffResponseDTO> getAll() {
        return workShopStaffRepository.findAll().stream().map(workShopStaffMapper::toResponse).toList();
    }

    @Override
    public WorkshopStaffResponseDTO getById(Long id) {
        return workShopStaffRepository.findById(id).
                map(workShopStaffMapper::toResponse).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
    }

    @Transactional
    public WorkshopStaffResponseDTO update(WorkshopStaffUpdateDTO request) {

        WorkshopStaffEntity employee = workShopStaffRepository.findByUserIdAndWorkshopId(request.getIdEmployee(), request.getIdWorkshop()).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        workShopStaffMapper.toEntityUpdate(request,employee);
        workShopStaffRepository.save(employee);

        if (employee.getRole() == StaffRole.OWNER) {
            throw new IncoherentDataException(
                    "The workshop owner role cannot be modified");
        }

        if (request.getRole()== StaffRole.OWNER) {
            throw new IncoherentDataException(
                    "Owner role cannot be assigned");
        }

        if (employee.getRole() == request.getRole()) {
            throw new IncoherentDataException(
                    "The employee already has this role");
        }
        employee.setRole(request.getRole());
        workShopStaffRepository.save(employee);

        return workShopStaffMapper.toResponse(employee);
    }

    @Override
    public WorkshopStaffResponseDTO delete(Long id) {
        WorkshopStaffEntity workShopStaff = workShopStaffRepository.findById(id).
                orElseThrow(()->new EntityNotFoundException("Staff not found"));
        WorkshopStaffResponseDTO response = workShopStaffMapper.toResponse(workShopStaff);
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

    public WorkshopStaffEntity getByEmployeeWorkshopStaff(Long idEmployee, Long  idWorkshop) {
        WorkshopStaffEntity staff = workShopStaffRepository.findByUserIdAndWorkshopId(idEmployee,idWorkshop)
                .orElseThrow(() -> new EntityNotFoundException("workhopStaff was not found for delete"));
        return  staff;
    }

    public WorkshopStaffResponseDTO deleteByEmployeeAndWorkshop(Long idEmployee, Long  idWorkshop) {
        WorkshopStaffEntity workShopStaff = workShopStaffRepository.findByUserIdAndWorkshopId(idEmployee,idWorkshop)
                .orElseThrow(() -> new EntityNotFoundException("workhopStaff was not found for delete"));
        WorkshopStaffResponseDTO response = workShopStaffMapper.toResponse(workShopStaff);
        workShopStaffRepository.delete(workShopStaff);
        return response;
    }


}
