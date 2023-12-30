package io.chou401.framework.utils;

import java.util.UUID;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/6/26
 **/
public class UUIDUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
