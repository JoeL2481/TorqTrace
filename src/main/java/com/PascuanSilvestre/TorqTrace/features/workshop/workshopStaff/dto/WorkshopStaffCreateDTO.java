package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.dto;

import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.enums.StaffRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkshopStaffCreateDTO {

    @NotNull(message = "Workshop ID is required")
    private Long workshopId;

    @NotNull(message = "User ID is required")
    private Long userId;

    private StaffRole role;

}
