package com.PascuanSilvestre.TorqTrace.features.user.user;

import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsRepository;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleRepository;
import com.PascuanSilvestre.TorqTrace.common.utils.ICrudServiceComplete;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.PascuanSilvestre.TorqTrace.auth.dto.NewAccountRequest;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleEntity;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements ICrudServiceComplete<UserCreateDTO,UserUpdateDTO,UserResponseDTO, UUID> {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CredentialsRepository credentialsRepository;

    public UserResponseDTO save(NewAccountRequest request) {

        if (credentialsRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity user = UserEntity.builder()
                .publicId(UUID.randomUUID())
                .firstName(request.username())
                .lastName("User")
                .passwordHash(passwordEncoder.encode(request.password()))
                .status(UserStatus.ACTIVE)
                .userContactInfo(new ContactInfo(null, request.email()))
                .build();

        UserEntity savedUser = repository.save(user);

        RoleEntity userRole = roleRepository.findAll().stream()
                .filter(role -> role.getRole() == Roles.ROLE_USER)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("ROLE_USER not found"));

        CredentialsEntity credentials = CredentialsEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .enabled(true)
                .usuario(savedUser)
                .roles(Set.of(userRole))
                .build();

        credentialsRepository.save(credentials);

        return mapper.toResponse(savedUser);
    }
    @Override
    public UserResponseDTO create(UserCreateDTO request) {
       UserEntity entity = mapper.toEntity(request);
       entity.setPublicId(UUID.randomUUID());
        entity.setStatus(UserStatus.ACTIVE);

        UserEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    /*CredentialsEntity newCredentials = CredentialsEntity.builder()
            .roles(Set.of(roleRepository.findById(1L).orElseThrow()))
            .enabled(true)
            .username()
            .password()
            .usuario()
            .build*/
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
