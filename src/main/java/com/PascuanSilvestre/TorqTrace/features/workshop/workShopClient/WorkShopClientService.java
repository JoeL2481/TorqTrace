package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto.WorkShopClientUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.mapper.WorkShopClientMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkShopClientService implements ICrudServiceComplete<WorkShopClientCreateDTO, WorkShopClientUpdateDTO, WorkShopClientResponseDTO,Long> {

    private final WorkShopClientRepository workShopClientRepository;
    private final WorkShopClientMapper workShopClientMapper;

    @Override
    public WorkShopClientResponseDTO create(WorkShopClientCreateDTO request) {
        WorkShopClientEntity workShopClient = workShopClientMapper.toEntity(request);
        WorkShopClientEntity workShopClientSave = workShopClientRepository.save(workShopClient);
        return workShopClientMapper.toResponse(workShopClientSave);
    }

    @Override
    public List<WorkShopClientResponseDTO> getAll() {
        return workShopClientRepository.findAll().
                stream().
                map(workShopClientMapper::toResponse).
                toList();
    }

    @Override
    public WorkShopClientResponseDTO getById(Long id) {
        return workShopClientRepository.findById(id).
                map(workShopClientMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
    }

    @Override
    public WorkShopClientResponseDTO update(Long id, WorkShopClientUpdateDTO request) {
        WorkShopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        workShopClientMapper.toEntityUpdate(request,workShopClient);
        return workShopClientMapper.toResponse(workShopClientRepository.save(workShopClient));

    }

    @Override
    public WorkShopClientResponseDTO delete(Long id) {
        WorkShopClientEntity workShopClient = workShopClientRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("workShop Client not found"));
        WorkShopClientResponseDTO response = workShopClientMapper.toResponse(workShopClient);
        workShopClientRepository.delete(workShopClient);
        return response;
    }
}