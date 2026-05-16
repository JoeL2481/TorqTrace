package com.PascuanSilvestre.TorqTrace.features.auth.userProvider;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.mapper.UserProviderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProviderService implements ICrudService<UserProviderCreateDTO, UserProviderResponseDTO,Long> {

    private final UserProviderRepository userProviderRepository;
    private final UserProviderMapper userProviderMapper;
    @Override
    public UserProviderResponseDTO create(UserProviderCreateDTO request) {
        UserProviderEntity userProvider = userProviderMapper.toEntity(request);
        UserProviderEntity savedEntity = userProviderRepository.save(userProvider);
        return userProviderMapper.toResponse(savedEntity);
    }

    @Override
    public List<UserProviderResponseDTO> getAll() {
        return userProviderRepository.findAll().stream()
                .map(userProviderMapper::toResponse)
                .toList();
    }

    @Override
    public UserProviderResponseDTO getById(Long id) {
        return userProviderRepository.findById(id).
                map(userProviderMapper::toResponse).
                orElseThrow(() -> new EntityNotFoundException("user provider was not found"));
    }

    @Override
    public UserProviderResponseDTO update(Long aLong, UserProviderCreateDTO request) {
        return null;
    }

    @Override
    public UserProviderResponseDTO delete(Long id) {
        UserProviderEntity userProviderEntity = userProviderRepository.findById(id).
                orElseThrow(() ->new EntityNotFoundException("User provider was not found for delete"));
        UserProviderResponseDTO response = userProviderMapper.toResponse(userProviderEntity);
        userProviderRepository.delete(userProviderEntity);
        return response;
    }
}
