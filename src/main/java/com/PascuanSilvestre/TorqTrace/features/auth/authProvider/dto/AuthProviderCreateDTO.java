package com.PascuanSilvestre.TorqTrace.features.auth.authProvider.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthProviderCreateDTO {


    @NotNull(message = "name is required")
    @Size(min = 1, max = 100)
    private String name;

    @NotNull(message = "display name is required")
    @Size(min = 1, max = 200)
    private String displayName;

    @NotNull(message = "user id is required")
    private Long userId;
}
