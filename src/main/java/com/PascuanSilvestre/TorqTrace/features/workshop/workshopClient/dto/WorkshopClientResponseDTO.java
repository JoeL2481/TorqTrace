package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopClientResponseDTO {

    private Long id;

    private UserResponseDTO user;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
