package com.PascuanSilvestre.TorqTrace.features.auth.userProvider.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import org.modelmapper.ModelMapper;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto.UserProviderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProviderMapper implements IMapper<UserProviderEntity, UserProviderCreateDTO, UserProviderResponseDTO> {

    private final ModelMapper modelMapper;
    @Override
    public UserProviderEntity toEntity(UserProviderCreateDTO request) {
        return modelMapper.map(request,UserProviderEntity.class);
    }

    @Override
    public UserProviderResponseDTO toResponse(UserProviderEntity entity) {
        return modelMapper.map(entity, UserProviderResponseDTO.class);
    }
}
