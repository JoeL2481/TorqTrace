package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopUpdateDTO;
import com.PascuanSilvestre.TorqTrace.common.exception.DuplicatedNameException;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper.WorkShopMapper;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkShopService implements ICrudServiceComplete<WorkShopCreateDTO, WorkShopUpdateDTO, WorkShopResponseDTO, Long> {

    private final WorkShopRepository repository;
    private final WorkShopMapper mapper;
    private final WorkShopStaffService workShopStaffService;
    private final SecurityUtils securityUtils;
    private final UserService userService;
    private final WorkshopPermissionService permissionService;

    @Override
    public WorkShopResponseDTO create(WorkShopCreateDTO request) {

        boolean exists = repository.existsByName(request.getName());
        if (exists) {
            throw new DuplicatedNameException("Workshop name already in use");
        }
        WorkShopEntity entity = mapper.toEntity(request);
        entity.setStatus(true);
        WorkShopEntity savedEntity = repository.save(entity);
        WorkShopStaffCreateDTO owner = WorkShopStaffCreateDTO.builder().workshopId(savedEntity.getId())
                .userId(securityUtils.getCurrentUserId())
                .role(StaffRole.OWNER)
                .build();
        workShopStaffService.create(owner);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public List<WorkShopResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public WorkShopResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("workShop not found"));
    }

    public WorkShopDetailedResponseDTO getWorkshopDetailsById(Long idWorkshop) {

        if (!permissionService.isManagerOrOwner(idWorkshop)) {
            throw new AccessDeniedException("Only the workshop owner or manager can perform this action");
        }

        WorkShopDetailedResponseDTO response = repository.findById(idWorkshop).map(mapper::toDetailedResponse).
                orElseThrow(() -> new EntityNotFoundException("workshop not found"));

        return response;
    }

    @Override
    public WorkShopResponseDTO update(Long idWorkshop, WorkShopUpdateDTO request) {

        if (!permissionService.isOwner(idWorkshop)) {
            throw new AccessDeniedException("Workshop does'nt exist or only the workshop owner or manager can perform this action");
        }

        WorkShopEntity entity = repository.findById(idWorkshop)
                .orElseThrow(() -> new EntityNotFoundException("workShop was not found for update"));



        mapper.toEntityUpdate(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public WorkShopResponseDTO delete(Long idWorkshop) {
        {
            WorkShopEntity entity = repository.findById(idWorkshop)
                    .orElseThrow(() -> new EntityNotFoundException("workShop was not found for delete"));

            if (!permissionService.isOwner(idWorkshop)) {
                throw new AccessDeniedException("Only the workshop owner can perform this action");
            }

            WorkShopResponseDTO response = mapper.toResponse(entity);
            repository.delete(entity);

            return response;

        }
    }

    @Transactional
    public WorkShopDetailedResponseDTO changeEmployeeRole(WorkShopStaffUpdateDTO request) {

        // Verifica que quien hace la petición sea OWNER
        if (!permissionService.isOwner(request.getIdWorkshop())) {
            throw new AccessDeniedException(
                    "Only the workshop owner can change employee roles");
        }

        workShopStaffService.update(request);
        return repository.findById(request.getIdWorkshop())
                .map(mapper::toDetailResponse)
                .orElseThrow(() ->
                        new EntityNotFoundException("Workshop not found"));
    }

    @Transactional
    public WorkShopDetailedResponseDTO addEmployee (Long idEmployee, Long idWorkshop, StaffRole role){

        if(!existWorkshop(idWorkshop)){
            throw new EntityNotFoundException("WorkShop not found");
        }
        if (!permissionService.isOwner(idWorkshop)) {
            throw new AccessDeniedException("Only the workshop owner can perform this action");
        }

        if(!userService.existUser(idEmployee)){
            throw new EntityNotFoundException("User not found");
        }

        if (workShopStaffService.existEmployeeWorkshopStaff(idEmployee,idWorkshop)){
            throw new EntityNotFoundException("This user is already a staff member of this workshop");
        }

        WorkShopStaffCreateDTO owner = WorkShopStaffCreateDTO.builder().workshopId(idWorkshop)
                .userId(securityUtils.getCurrentUserId())
                .role(role)
                .build();
        workShopStaffService.create(owner);

        WorkShopDetailedResponseDTO workshop = repository.findById(idWorkshop).map(mapper::toDetailResponse).
                orElseThrow(() -> new EntityNotFoundException("workShop not found"));
        return workshop;
    }

    @Transactional
    public WorkShopDetailedResponseDTO removeEmployee (Long idEmployee, Long idWorkshop){

        if(!existWorkshop(idWorkshop)){
            throw new EntityNotFoundException("WorkShop not found");
        }
        if (!permissionService.isOwner(idWorkshop)) {
            throw new AccessDeniedException("Only the workshop owner can perform this action");
        }
        if(!userService.existUser(idEmployee)){
            throw new EntityNotFoundException("User not found");
        }
        workShopStaffService.deleteByEmployeeAndWorkshop(idEmployee, idWorkshop);
        WorkShopDetailedResponseDTO workshop = repository.findById(idWorkshop).map(mapper::toDetailResponse).
                orElseThrow(() -> new EntityNotFoundException("workShop not found"));
        return workshop;
    }

    public boolean existWorkshop(Long id) {
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("Workshop not found");
        }
        return true;
    }
}
