package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.enums;

public enum ECurrency {

    USD("United States Dollar"),
    EUR("Euro"),
    GBP("British Pound Sterling"),
    JPY("Japanese Yen"),
    ARS("Argentine Peso");

    private final String description;

    ECurrency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
