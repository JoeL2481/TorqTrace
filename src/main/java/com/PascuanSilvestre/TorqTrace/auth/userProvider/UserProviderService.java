package com.PascuanSilvestre.TorqTrace.auth.userProvider;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderRepository;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudService;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.dto.UserProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.dto.UserProviderResponseDTO;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.mapper.UserProviderMapper;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProviderService implements ICrudService<UserProviderCreateDTO, UserProviderResponseDTO,Long> {

    private final UserProviderRepository userProviderRepository;
    private final UserProviderMapper userProviderMapper;
    private final UserRepository userRepository;
    private final AuthProviderRepository authProviderRepository;

    @Override
    public UserProviderResponseDTO create(UserProviderCreateDTO request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        AuthProviderEntity provider = authProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new EntityNotFoundException("Auth provider not found"));

        UserProviderEntity userProvider = userProviderMapper.toEntity(request);
        userProvider.setUser(user);
        userProvider.setProvider(provider);

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
