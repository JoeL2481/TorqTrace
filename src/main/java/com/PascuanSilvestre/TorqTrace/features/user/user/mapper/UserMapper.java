package com.PascuanSilvestre.TorqTrace.features.user.user.mapper;


import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper implements IUserMapper<UserEntity, UserCreateDTO, UserUpdateDTO, UserResponseDTO, UserDetailedResponseDTO> {
    private final ModelMapper mapper;
    @Override
    public UserEntity toEntity(UserCreateDTO request) {
        return mapper.map(request, UserEntity.class);
    }

    @Override
    public UserResponseDTO toResponse(UserEntity entity) {
        return mapper.map(entity, UserResponseDTO.class);


    }


    public UserEntity toEntityUpdate(UserUpdateDTO request, UserEntity entity) {
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        if (request.getAvatarUrl() != null) {
            entity.setAvatarUrl(request.getAvatarUrl());
        }
        if (request.getUserAddress() != null) {
            entity.setUserAddress(mapper.map(request.getUserAddress(), AddressInfo.class));
        }
        if (request.getUserContactInfo() != null) {
            entity.setUserContactInfo(mapper.map(request.getUserContactInfo(), ContactInfo.class));
        }

        return entity;
    }

    public UserDetailedResponseDTO toDetailedResponse(UserEntity entity) {
        return mapper.map(entity, UserDetailedResponseDTO.class);
    }
}
