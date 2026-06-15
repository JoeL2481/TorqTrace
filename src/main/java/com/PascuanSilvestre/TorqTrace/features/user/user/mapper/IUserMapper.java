package com.PascuanSilvestre.TorqTrace.features.user.user.mapper;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;

public interface IUserMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO, DetailedResponseDTO> {
    Entity toEntity(CreateDTO request);

    ResponseDTO toResponse(Entity entity);

    Entity toEntityUpdate(UpdateDTO request, Entity entity);

    DetailedResponseDTO toDetailedResponse(Entity entity);
}
