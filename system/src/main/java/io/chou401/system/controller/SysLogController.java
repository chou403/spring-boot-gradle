package io.chou401.system.controller;

import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysLogQuery;
import io.chou401.common.vo.sys.SysLogVo;
import io.chou401.framework.annotation.Permission;
import io.chou401.framework.response.ApiResult;
import io.chou401.system.service.SysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志 控制器
 * {@code @author}  chou401
 * {@code @date} 2023-02-16
 */
@Slf4j
@RestController
@Tag(name = "系统日志")
@RequestMapping("/admin/sysLog")
public class SysLogController {

    private final SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    /**
     * 获取系统日志详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLog/{id}")
    @Operation(summary = "系统日志详情")
    @Permission("sys:log:info")
    public ApiResult<SysLogVo> getSysLog(@PathVariable Long id) throws Exception {
        SysLogVo sysLogVo = sysLogService.getSysLogById(id);
        return ApiResult.success(sysLogVo);
    }

    /**
     * 系统日志分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysLogPage")
    @Operation(summary = "系统日志分页列表")
    @Permission("sys:log:page")
    public ApiResult<Paging<SysLogVo>> getSysLogPage(@Valid @RequestBody SysLogQuery query) throws Exception {
        Paging<SysLogVo> paging = sysLogService.getSysLogPage(query);
        return ApiResult.success(paging);
    }

}
