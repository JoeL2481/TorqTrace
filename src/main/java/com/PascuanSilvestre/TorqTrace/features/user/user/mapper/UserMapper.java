package com.PascuanSilvestre.TorqTrace.features.user.user.mapper;


import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.common.IMapper;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserDetailedResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserUpdateDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper implements IMapper<UserEntity, UserCreateDTO, UserResponseDTO> {
    private final ModelMapper mapper;
    @Override
    public UserEntity toEntity(UserCreateDTO request) {
        return mapper.map(request, UserEntity.class);
    }

    @Override
    public UserResponseDTO toResponse(UserEntity entity) {
        return mapper.map(entity, UserResponseDTO.class);


    }


    public void toEntityUpdate(UserUpdateDTO request, UserEntity entity) {
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        if (request.getPassword() != null) {
            entity.setPasswordHash(request.getPassword());
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
    }

    public UserDetailedResponseDTO toDetailedResponse(UserEntity entity) {
        return mapper.map(entity, UserDetailedResponseDTO.class);
    }
}
