package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.dto.WorkShopResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopStaffResponseDTO {

    private Long id;

    private WorkShopResponseDTO workShop;

    private UserResponseDTO user;

    private StaffRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
