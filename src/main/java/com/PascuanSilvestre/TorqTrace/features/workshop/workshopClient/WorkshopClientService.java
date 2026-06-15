package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.mapper.WorkshopClientMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopClientService implements IWorkshopClientService<WorkshopClientCreateDTO, WorkshopClientUpdateDTO, WorkshopClientResponseDTO,Long> {

    private final WorkshopClientRepository workShopClientRepository;
    private final WorkshopClientMapper workShopClientMapper;
    private final WorkShopService  workShopService;
    private final UserService userService;
    private final WorkshopPermissionService  workshopPermissionService;

    @Override
    public WorkshopClientResponseDTO create(WorkshopClientCreateDTO request) {

        workShopService.existWorkshop(request.getWorkshopId());
        userService.existUser(request.getUserId());
        WorkshopClientEntity workShopClient = workShopClientMapper.toEntity(request);
        WorkshopClientEntity workShopClientSave = workShopClientRepository.save(workShopClient);
        return workShopClientMapper.toResponse(workShopClientSave);
    }

    @Override
    public List<WorkshopClientResponseDTO> getAll(Long workshopId) {

        workshopPermissionService.isManagerOrOwner(workshopId);
        return workShopClientRepository.findByWorkshop(workshopId).
                stream().
                map(workShopClientMapper::toResponse).
                toList();
    }

    @Override
    public WorkshopClientResponseDTO getById(Long id) {
        WorkshopClientResponseDTO response = workShopClientRepository.findById(id).
                map(workShopClientMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(response.getWorkShop().getId());
        return response;
    }

    public WorkshopClientResponseDTO getByUserId(Long userId) {
        WorkshopClientResponseDTO response = workShopClientRepository.findByUserId(userId).
                map(workShopClientMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(response.getWorkShop().getId());
        return response;
    }

    @Override
    public WorkshopClientResponseDTO update(Long id, WorkshopClientUpdateDTO request) {
        WorkshopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        workShopClientMapper.toEntityUpdate(request,workShopClient);
        return workShopClientMapper.toResponse(workShopClientRepository.save(workShopClient));

    }

    @Override
    public WorkshopClientResponseDTO delete(Long id) {
        WorkshopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        WorkshopClientResponseDTO response = workShopClientMapper.toResponse(workShopClient);
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