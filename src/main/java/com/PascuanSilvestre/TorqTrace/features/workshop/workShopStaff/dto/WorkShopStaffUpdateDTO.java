package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkShopStaffUpdateDTO {


    @NotNull(message = "Workshop id is required")
    @Positive(message = "Workshop id must be greater than 0")
    private Long idWorkshop;

    @NotNull(message = "Employee id is required")
    @Positive(message = "Employee id must be greater than 0")
    private Long idEmployee;

    @NotNull(message = "Role is required")
    private StaffRole role;
}
