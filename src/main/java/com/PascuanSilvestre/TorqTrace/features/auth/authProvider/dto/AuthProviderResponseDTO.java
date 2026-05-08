package com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto;

import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class AuthProviderResponseDTO {
    private Long id;
    private String name;
    private String display_name;
    List<UserProviderEntity> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
