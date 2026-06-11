package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto.WorkShopStaffCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopUpdateDTO;
import com.PascuanSilvestre.TorqTrace.common.exception.DuplicatedNameException;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.mapper.WorkShopMapper;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkShopService implements ICrudServiceComplete<WorkShopCreateDTO, WorkShopUpdateDTO, WorkShopResponseDTO, Long> {

    private final WorkShopRepository repository;
    private final WorkShopMapper mapper;
    private final WorkShopStaffService workShopStaff;
    private final SecurityUtils securityUtils;

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
        workShopStaff.create(owner);
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

    public WorkShopDetailedResponseDTO getWorkshopDetailsById(Long id) {
        return repository.findById(id)
                .map(mapper::toDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("workShop not found"));
    }

    @Override
    public WorkShopResponseDTO update(Long id, WorkShopUpdateDTO request) {
        WorkShopEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("workShop was not found for update"));

        mapper.toEntityUpdate(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public WorkShopResponseDTO delete(Long id) {
        {

            WorkShopEntity entity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("workShop was not found for delete"));

            WorkShopResponseDTO response = mapper.toResponse(entity);
            repository.delete(entity);

            return response;

        }
    }

    public boolean existWorkshop(Long id) {
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("Workshop not found");
        }
        return true;
    }
}
