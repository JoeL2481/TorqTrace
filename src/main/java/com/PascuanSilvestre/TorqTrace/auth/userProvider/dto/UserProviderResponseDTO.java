package com.PascuanSilvestre.TorqTrace.auth.userProvider.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProviderResponseDTO {

    private UserResponseDTO user;
    private String externalId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
