package com.PascuanSilvestre.TorqTrace.features.auth.authProvider;

import com.PascuanSilvestre.TorqTrace.common.ICrudService;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.mapper.AuthProviderMapper;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthProviderService implements ICrudService<AuthProviderCreateDTO, AuthProviderResponseDTO,Long> {

    private final AuthProviderRepository authRepository;
    private final AuthProviderMapper authMapper;
    @Override
    public AuthProviderResponseDTO create(AuthProviderCreateDTO request) {
        AuthProviderEntity authProviderEntity = authMapper.toEntity(request);

        authProviderEntity.setName(authProviderEntity.getName());
        authProviderEntity.setDisplayName(authProviderEntity.getDisplayName());
        // esto es probable que no tenga que ser asi
        authProviderEntity.setUsers(authProviderEntity.getUsers());

        AuthProviderEntity savedEntity = authRepository.save(authProviderEntity);
        return authMapper.toResponse(savedEntity);
    }

    @Override
    public List<AuthProviderResponseDTO> getAll() {
        return authRepository.findAll()
                .stream()
                .map(authMapper::toResponse)
                .toList();
    }

    @Override
    public AuthProviderResponseDTO getById(Long id) {
        return authRepository.findById(id)
                .map(authMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("auth provider was not found for that id"));
    }

    @Override
    public AuthProviderResponseDTO update(Long aLong, AuthProviderCreateDTO request) {
        return null;
    }

    @Override
    public AuthProviderResponseDTO delete(Long id) {
        AuthProviderEntity authEntity= authRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auth provider was not found for delete"));
        AuthProviderResponseDTO response= authMapper.toResponse(authEntity);
        authRepository.delete(authEntity);
        return response;
    }
}
