package com.PascuanSilvestre.TorqTrace.features.auth.userProvider.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserProviderCreateDTO {

    @NotNull(message="user is required")
    private Long userId;

    @NotNull(message="provider is required")
    private Long providerId;

    @NotNull(message="external id is required")
    @Size(min=1, max=255)
    private String externalId;

}
