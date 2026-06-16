package com.PascuanSilvestre.TorqTrace.auth.authProvider.dto;

import com.PascuanSilvestre.TorqTrace.auth.userProvider.dto.UserProviderResponseDTO;
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
    List<UserProviderResponseDTO> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
