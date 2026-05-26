package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.dto;

import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkShopStaffUpdateDTO {


    @NotNull
    private StaffRole role;

}
