package io.chou401.common.vo.sys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * App字典数据VO
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Data
@Schema(description = "App字典数据查询结果")
public class SysDictAppVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "字典名称")
    private String label;

    @Schema(description = "字典类型code")
    private String dictCode;

}

