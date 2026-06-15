package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import com.PascuanSilvestre.TorqTrace.auth.config.SecurityUtils;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffRepository;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums.StaffRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkshopPermissionService {

    private final WorkShopStaffRepository repository;
    private final SecurityUtils securityUtils;

    public boolean isOwner(Long workshopId) {

        Long userId = securityUtils.getCurrentUserId();

        return repository
                .findByUserIdAndWorkshopId(userId, workshopId)
                .map(staff -> staff.getRole() == StaffRole.OWNER)
                .orElse(false);
    }

    public boolean isManagerOrOwner(Long workshopId) {

        Long userId = securityUtils.getCurrentUserId();

        return repository
                .findByUserIdAndWorkshopId(userId, workshopId)
                .map(staff ->
                        staff.getRole() == StaffRole.OWNER ||
                                staff.getRole() == StaffRole.MANAGER)
                .orElse(false);
    }

    public boolean isManagerOrOwnerOrMechanic(Long workshopId) {

        Long userId = securityUtils.getCurrentUserId();

        return repository
                .findByUserIdAndWorkshopId(userId, workshopId)
                .map(staff ->
                        staff.getRole() == StaffRole.OWNER ||
                                staff.getRole() == StaffRole.MANAGER ||staff.getRole() == StaffRole.MECHANIC)
                .orElse(false);
    }


}
