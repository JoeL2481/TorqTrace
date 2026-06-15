package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;

import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.mapper.WorkShopClientMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkShopClientService implements IWorkshopClientService<WorkShopClientCreateDTO, WorkShopClientUpdateDTO, WorkShopClientResponseDTO,Long> {

    private final WorkShopClientRepository workShopClientRepository;
    private final WorkShopClientMapper workShopClientMapper;
    private final WorkShopService  workShopService;
    private final UserService userService;
    private final WorkshopPermissionService  workshopPermissionService;

    @Override
    public WorkShopClientResponseDTO create(WorkShopClientCreateDTO request) {

        workShopService.existWorkshop(request.getWorkshopId());
        userService.existUser(request.getUserId());
        WorkShopClientEntity workShopClient = workShopClientMapper.toEntity(request);
        WorkShopClientEntity workShopClientSave = workShopClientRepository.save(workShopClient);
        return workShopClientMapper.toResponse(workShopClientSave);
    }

    @Override
    public List<WorkShopClientResponseDTO> getAll(Long workshopId) {

        workshopPermissionService.isManagerOrOwner(workshopId);
        return workShopClientRepository.findByWorkshop(workshopId).
                stream().
                map(workShopClientMapper::toResponse).
                toList();
    }

    @Override
    public WorkShopClientResponseDTO getById(Long id) {
        WorkShopClientResponseDTO response = workShopClientRepository.findById(id).
                map(workShopClientMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(response.getWorkShop().getId());
        return response;
    }

    public WorkShopClientResponseDTO getByUserId(Long userId) {
        WorkShopClientResponseDTO response = workShopClientRepository.findByUserId(userId).
                map(workShopClientMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(response.getWorkShop().getId());
        return response;
    }

    @Override
    public WorkShopClientResponseDTO update(Long id, WorkShopClientUpdateDTO request) {
        WorkShopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        workShopClientMapper.toEntityUpdate(request,workShopClient);
        return workShopClientMapper.toResponse(workShopClientRepository.save(workShopClient));

    }

    @Override
    public WorkShopClientResponseDTO delete(Long id) {
        WorkShopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        WorkShopClientResponseDTO response = workShopClientMapper.toResponse(workShopClient);
        workShopClientRepository.delete(workShopClient);
        return response;
    }

    public boolean existClientByWorkshopAndUser(Long userId, Long workshopId ) {
        if (!workShopClientRepository.existsByUserIdAndWorkshopId(userId, workshopId)) {
            return false;
        }
        return true;
    }

}