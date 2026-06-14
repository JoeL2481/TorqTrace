package com.PascuanSilvestre.TorqTrace.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationDTO {

    public boolean exactlyOne(Object first, Object second) {
        boolean firstExist = isPresent(first);
        boolean secondExist = isPresent(second);

        if (firstExist && !secondExist) {
            return true;
        }

        if (!firstExist && secondExist) {
            return true;
        }

        return false;
    }

    public boolean onlyOneOrNone(Object first, Object second) {
        boolean firstExist = isPresent(first);
        boolean secondExist = isPresent(second);
        boolean result = true;

        if (firstExist && secondExist) {
            result = false;
        }

        return result;
    }

    public boolean isPresent(Object content) {
        if (content == null) {
            return false;
        }

        if (content instanceof String text) {
            if (text.isBlank()) {
                return false;
            }
        }

        return true;
    }
}