package com.PascuanSilvestre.TorqTrace.common.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.Random;

@UtilityClass
public class ShortPublicIdGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public String generate() {
        String id = "";

        for (int i = 0; i < 16; i++) {
            int index = RANDOM.nextInt(CHARS.length());
            id += CHARS.charAt(index);
        }

        return id;
    }
}