package io.chou401.common.enums;

import lombok.Getter;

/**
 * 系统类型
 * {@code @author}  chou401
 * {@code @date} 2023/11/15
 **/
@Getter
public enum FileServerType {

    LOCAL(1, "本地文件服务"),
    OSS(2, "OSS文件服务");

    private final Integer code;
    private final String desc;

    FileServerType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FileServerType getFileServerType(Integer code) {
        for (FileServerType fileServerType : values()) {
            if (fileServerType.getCode().equals(code)) {
                return fileServerType;
            }
        }
        return null;
    }

}
