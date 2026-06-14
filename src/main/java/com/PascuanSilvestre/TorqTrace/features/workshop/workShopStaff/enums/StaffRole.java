package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.enums;

public enum StaffRole {
    OWNER(3),
    MANAGER(2),
    MECHANIC(1);

    private final int level;

    StaffRole(int level) {
        this.level = level;
    }

    public boolean hasPermission(StaffRole required) {
        return this.level >= required.level;
    }
}