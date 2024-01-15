package io.chou401.system.controller;

import io.chou401.common.enums.SysLogType;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysDictQuery;
import io.chou401.common.vo.sys.SysDictVo;
import io.chou401.framework.annotation.Log;
import io.chou401.framework.annotation.Permission;
import io.chou401.framework.response.ApiResult;
import io.chou401.system.dto.SysDictDto;
import io.chou401.system.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 字典数据 控制器
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "字典数据")
@RequestMapping("/admin/sysDict")
public class SysDictController {

    private final SysDictService sysDictService;

    public SysDictController(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * 添加字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加字典数据", type = SysLogType.ADD)
    @PostMapping("/addSysDict")
    @Operation(summary = "添加字典数据")
    @Permission("sys:dict:add")
    public ApiResult addSysDict(@Valid @RequestBody SysDictDto dto) throws Exception {
        boolean flag = sysDictService.addSysDict(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改字典数据", type = SysLogType.UPDATE)
    @PostMapping("/updateSysDict")
    @Operation(summary = "修改字典数据")
    @Permission("sys:dict:update")
    public ApiResult updateSysDict(@Valid @RequestBody SysDictDto dto) throws Exception {
        boolean flag = sysDictService.updateSysDict(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除字典数据", type = SysLogType.DELETE)
    @PostMapping("/deleteSysDict/{id}")
    @Operation(summary = "删除字典数据")
    @Permission("sys:dict:delete")
    public ApiResult deleteSysDict(@PathVariable Long id) throws Exception {
        boolean flag = sysDictService.deleteSysDict(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取字典数据详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDict/{id}")
    @Operation(summary = "字典数据详情")
    @Permission("sys:dict:info")
    public ApiResult<SysDictVo> getSysDict(@PathVariable Long id) throws Exception {
        SysDictVo sysDictVo = sysDictService.getSysDictById(id);
        return ApiResult.success(sysDictVo);
    }

    /**
     * 字典数据分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDictPage")
    @Operation(summary = "字典数据分页列表")
    @Permission("sys:dict:page")
    public ApiResult<Paging<SysDictVo>> getSysDictPage(@Valid @RequestBody SysDictQuery query) throws Exception {
        Paging<SysDictVo> paging = sysDictService.getSysDictPage(query);
        return ApiResult.success(paging);
    }

    /**
     * 根据字典编码和label获取字典值
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDictLabelByValue")
    @Operation(summary = "字典编码和label获取字典值")
    public ApiResult<String> getSysDictLabelByValue(@RequestParam String dictCode, @RequestParam Integer value) throws Exception {
        String res = sysDictService.getSysDictLabelByValue(dictCode, value);
        return ApiResult.success(res);
    }

}
