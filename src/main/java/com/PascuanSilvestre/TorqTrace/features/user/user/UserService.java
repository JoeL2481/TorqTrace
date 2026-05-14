package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.common.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements ICrudServiceComplete<UserCreateDTO,UserUpdateDTO,UserResponseDTO, UUID> {

    private final UserRepository repository;
    private final UserMapper mapper;


    @Override
    public UserResponseDTO create(UserCreateDTO request) {
       UserEntity entity = mapper.toEntity(request);
       entity.setPublicId(UUID.randomUUID());
        entity.setStatus(UserStatus.ACTIVE);

        UserEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getById(UUID id) {
        return repository.findByPublicId(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("User was not found for that uuid"));
    }

    public UserDetailedResponseDTO getDetailedByID(UUID id) {
        return repository.findByPublicId(id)
                .map(mapper::toDetailedResponse)
                .orElseThrow(() -> new EntityNotFoundException("Detailed User info was not found for that uuid"));
    }

    @Override
    public UserResponseDTO update(UUID id, UserUpdateDTO request) {
        UserEntity entity = repository.findByPublicId(id)
                .orElseThrow(() -> new EntityNotFoundException("User was not found for update"));

        mapper.toEntityUpdate(request,entity);
        return mapper.toResponse(repository.save(entity));

    }

    @Override
    public UserResponseDTO delete(UUID id) {

        UserEntity entity= repository.findByPublicId(id)
                .orElseThrow(() -> new EntityNotFoundException("User was not found for delete"));

        UserResponseDTO response= mapper.toResponse(entity);
        repository.delete(entity);

        return response;

    }
}
