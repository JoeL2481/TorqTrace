package com.PascuanSilvestre.TorqTrace.features.auth.authProvider.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import org.modelmapper.ModelMapper;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderRequestDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProviderMapper implements IMapper<AuthProviderEntity, AuthProviderRequestDTO, AuthProviderResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public AuthProviderEntity toEntity(AuthProviderRequestDTO request) {
        return modelMapper.map(request, AuthProviderEntity.class);
    }

    @Override
    public AuthProviderResponseDTO toResponse(AuthProviderEntity entity) {
        return modelMapper.map(entity, AuthProviderResponseDTO.class);
    }
}
