package io.chou401.common.enums;

import lombok.Getter;

/**
 * 系统类型
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023/11/15
 **/
@Getter
public enum SystemType {

    ADMIN(1, "Admin管理后台"),
    APP(2, "APP移动端端");

    private final Integer code;
    private final String desc;

    SystemType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SystemType getSystemType(Integer code) {
        for (SystemType systemType : values()) {
            if (systemType.getCode().equals(code)) {
                return systemType;
            }
        }
        return null;
    }

}
