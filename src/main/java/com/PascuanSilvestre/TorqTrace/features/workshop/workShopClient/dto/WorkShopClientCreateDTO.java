package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopClientCreateDTO {

    @NotNull(message="workshop is required")
    private Long workshopId;

    @NotNull(message = "user is required")
    private Long userId;

    private String description;

}

