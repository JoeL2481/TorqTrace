package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopClientCreateDTO {

    @NotNull(message="workshop is required")
    private Long workshopId;

    @NotNull(message = "user is required")
    private Long userId;

    private String description;

}

