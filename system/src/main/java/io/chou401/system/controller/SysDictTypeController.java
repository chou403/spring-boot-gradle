package io.chou401.system.controller;

import io.chou401.common.enums.SysLogType;
import io.chou401.common.query.sys.SysDictTypeQuery;
import io.chou401.common.vo.sys.SysDictTypeVo;
import io.chou401.framework.annotation.Log;
import io.chou401.framework.annotation.Permission;
import io.chou401.framework.response.ApiResult;
import io.chou401.system.dto.SysDictTypeDto;
import io.chou401.system.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典类型 控制器
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "字典类型")
@RequestMapping("/admin/sysDictType")
public class SysDictTypeController {

    private final SysDictTypeService sysDictTypeService;

    public SysDictTypeController(SysDictTypeService sysDictTypeService) {
        this.sysDictTypeService = sysDictTypeService;
    }

    /**
     * 添加字典类型
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "添加字典类型", type = SysLogType.ADD)
    @PostMapping("/addSysDictType")
    @Operation(summary = "添加字典类型")
    @Permission("sys:dict:type:add")
    public ApiResult addSysDictType(@Valid @RequestBody SysDictTypeDto dto) throws Exception {
        boolean flag = sysDictTypeService.addSysDictType(dto);
        return ApiResult.result(flag);
    }

    /**
     * 修改字典类型
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改字典类型", type = SysLogType.UPDATE)
    @PostMapping("/updateSysDictType")
    @Operation(summary = "修改字典类型")
    @Permission("sys:dict:type:update")
    public ApiResult updateSysDictType(@Valid @RequestBody SysDictTypeDto dto) throws Exception {
        boolean flag = sysDictTypeService.updateSysDictType(dto);
        return ApiResult.result(flag);
    }

    /**
     * 删除字典类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(value = "删除字典类型", type = SysLogType.DELETE)
    @PostMapping("/deleteSysDictType/{id}")
    @Operation(summary = "删除字典类型")
    @Permission("sys:dict:type:delete")
    public ApiResult deleteSysDictType(@PathVariable Long id) throws Exception {
        boolean flag = sysDictTypeService.deleteSysDictType(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取字典类型详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDictType/{id}")
    @Operation(summary = "字典类型详情")
    @Permission("sys:dict:type:info")
    public ApiResult<SysDictTypeVo> getSysDictType(@PathVariable Long id) throws Exception {
        SysDictTypeVo sysDictTypeVo = sysDictTypeService.getSysDictTypeById(id);
        return ApiResult.success(sysDictTypeVo);
    }

    /**
     * 字典类型列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getSysDictTypeList")
    @Operation(summary = "字典类型列表")
    @Permission("sys:dict:type:list")
    public ApiResult<SysDictTypeVo> getSysDictTypeList(@Valid @RequestBody SysDictTypeQuery query) throws Exception {
        List<SysDictTypeVo> list = sysDictTypeService.getSysDictTypeList(query);
        return ApiResult.success(list);
    }

}
