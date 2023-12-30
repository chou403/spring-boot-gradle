package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysDictType;
import io.chou401.common.query.sys.SysDictTypeQuery;
import io.chou401.common.vo.sys.SysDictTypeVo;
import io.chou401.system.dto.SysDictTypeDto;

import java.util.List;

/**
 * 字典类型 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 添加字典类型
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysDictType(SysDictTypeDto dto) throws Exception;

    /**
     * 修改字典类型
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysDictType(SysDictTypeDto dto) throws Exception;

    /**
     * 删除字典类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysDictType(Long id) throws Exception;

    /**
     * 字典类型详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysDictTypeVo getSysDictTypeById(Long id) throws Exception;

    /**
     * 字典类型列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query) throws Exception;

    /**
     * 检查code是否存在
     *
     * @param code
     * @return
     * @throws Exception
     */
    void checkCodeExists(String code) throws Exception;

}
