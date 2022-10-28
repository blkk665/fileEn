package com.blkk665.fileen.utils;

import java.util.UUID;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/17
 */
public class UUIDStringUtil {
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }
}
