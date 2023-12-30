package io.chou401.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysDict;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysDictAppQuery;
import io.chou401.common.query.sys.SysDictQuery;
import io.chou401.common.vo.sys.SysDictAppVo;
import io.chou401.common.vo.sys.SysDictVo;
import io.chou401.system.dto.SysDictDto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 字典数据 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 添加字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysDict(SysDictDto dto) throws Exception;

    /**
     * 修改字典数据
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysDict(SysDictDto dto) throws Exception;

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysDict(Long id) throws Exception;

    /**
     * 字典数据详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysDictVo getSysDictById(Long id) throws Exception;

    /**
     * 字典数据分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysDictVo> getSysDictPage(SysDictQuery query) throws Exception;

    /**
     * App字典数据列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Map<String, List<SysDictAppVo>> getAppSysDictList(SysDictAppQuery query) throws Exception;

    /**
     * 根据字典编码获取字典列表
     *
     * @param dictCode
     * @return
     * @throws Exception
     */
    List<SysDict> getSysDictList(String dictCode) throws Exception;

    /**
     * 根据字典编码和label获取字典对象
     *
     * @param dictCode
     * @param value
     * @return
     * @throws Exception
     */
    SysDict getSysDictByValue(String dictCode, Serializable value) throws Exception;

    /**
     * 根据字典编码和label获取字典值
     *
     * @param dictCode
     * @param value
     * @return
     * @throws Exception
     */
    String getSysDictLabelByValue(String dictCode, Serializable value) throws Exception;

}
