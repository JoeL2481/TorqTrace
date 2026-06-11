package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkShopStaffCreateDTO {

    @NotNull(message = "Workshop ID is required")
    private Long workshopId;

    @NotNull(message = "User ID is required")
    private Long userId;

    private StaffRole role;

}
