package com.PascuanSilvestre.TorqTrace.features.auth.authProvider.mapper;

import com.PascuanSilvestre.TorqTrace.common.IMapper;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderCreateDTO;
import com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto.AuthProviderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProviderMapper implements IMapper<AuthProviderEntity, AuthProviderCreateDTO, AuthProviderResponseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public AuthProviderEntity toEntity(AuthProviderCreateDTO request) {
        return modelMapper.map(request, AuthProviderEntity.class);
    }

    @Override
    public AuthProviderResponseDTO toResponse(AuthProviderEntity entity) {
        return modelMapper.map(entity, AuthProviderResponseDTO.class);
    }
}
