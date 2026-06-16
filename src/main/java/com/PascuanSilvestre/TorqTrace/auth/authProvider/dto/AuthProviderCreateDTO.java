package com.PascuanSilvestre.TorqTrace.auth.authProvider.dto;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthProviderCreateDTO {

    @NotNull(message = "name is required")
    private EAuthProviders name;

    @NotNull(message = "display name is required")
    private String displayName;
}
