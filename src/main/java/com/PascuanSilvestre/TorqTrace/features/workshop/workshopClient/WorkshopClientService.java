package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.common.exception.AlreadyExistsException;
import com.PascuanSilvestre.TorqTrace.common.exception.ProhibitedOperationException;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto.WorkshopClientUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.mapper.WorkshopClientMapper;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopService;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopPermissionService;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserRepository;
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
    private final WorkShopRepository workShopRepository;
    private final UserRepository userRepository;
    private final WorkshopPermissionService  workshopPermissionService;

    @Override
    public WorkshopClientResponseDTO create(WorkshopClientCreateDTO request) {
        if (!workshopPermissionService.isManagerOrOwner(request.getWorkshopId())) {
            throw new ProhibitedOperationException("Only the workshop owner or manager can perform this action");
        }

        workShopService.existWorkshop(request.getWorkshopId());
        userService.existUser(request.getUserId());

        if (workShopClientRepository.existsByUserIdAndWorkshopId(request.getUserId(), request.getWorkshopId())) {
            throw new AlreadyExistsException("This user is already a client of this workshop");
        }

        WorkShopEntity workshop = workShopRepository.findById(request.getWorkshopId())
                .orElseThrow(() -> new EntityNotFoundException("Workshop not found"));
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        WorkshopClientEntity workShopClient = workShopClientMapper.toEntity(request);
        workShopClient.setWorkshop(workshop);
        workShopClient.setUser(user);
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
        WorkshopClientEntity workShopClient = workShopClientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        return workShopClientMapper.toResponse(workShopClient);
    }

    public WorkshopClientResponseDTO getByUserId(Long userId) {
        WorkshopClientEntity workShopClient = workShopClientRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Workshop Client not found"));
        workshopPermissionService.isManagerOrOwner(workShopClient.getWorkshop().getId());
        return workShopClientMapper.toResponse(workShopClient);
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
        return workShopClientRepository.existsByUserIdAndWorkshopId(userId, workshopId);
    }

}
