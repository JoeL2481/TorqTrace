package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkShopClientResponseDTO {

    private Long id;

    private UserResponseDTO user;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
