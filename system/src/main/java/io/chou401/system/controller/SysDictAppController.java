package io.chou401.system.controller;

import io.chou401.common.query.sys.SysDictAppQuery;
import io.chou401.common.vo.sys.SysDictAppVo;
import io.chou401.framework.response.ApiResult;
import io.chou401.system.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * App字典数据 控制器
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "App字典数据")
@RequestMapping("/app/sysDict")
public class SysDictAppController {

    private final SysDictService sysDictService;

    public SysDictAppController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * App字典数据列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppSysDictList")
    @Operation(summary = "App字典数据列表")
    public ApiResult<SysDictAppVo> getAppSysDictList(@Valid @RequestBody SysDictAppQuery query) throws Exception {
        Map<String,List<SysDictAppVo>> map = sysDictService.getAppSysDictList(query);
        return ApiResult.success(map);
    }

}
