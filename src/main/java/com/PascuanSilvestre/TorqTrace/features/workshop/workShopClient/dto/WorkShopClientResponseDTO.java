package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopClientResponseDTO {

    private Long id;

    private WorkShopResponseDTO workShop;

    private UserResponseDTO user;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
