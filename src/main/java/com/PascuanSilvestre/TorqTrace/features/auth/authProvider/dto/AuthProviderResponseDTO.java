package com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto;

import com.PascuanSilvestre.TorqTrace.features.auth.userProvider.UserProviderEntity;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthProviderResponseDTO {
    private Long id;
    private String name;
    private String displayName;
    List<UserProviderEntity> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
