package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysConfig;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysConfigQuery;
import io.chou401.common.vo.sys.SysConfigVo;
import io.chou401.system.dto.SysConfigDto;

/**
 * 系统配置 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-27
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 添加系统配置
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysConfig(SysConfigDto dto) throws Exception;

    /**
     * 修改系统配置
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysConfig(SysConfigDto dto) throws Exception;

    /**
     * 删除系统配置
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysConfig(Long id) throws Exception;

    /**
     * 系统配置详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysConfigVo getSysConfigById(Long id) throws Exception;

    /**
     * 系统配置分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysConfigVo> getSysConfigPage(SysConfigQuery query) throws Exception;

    /**
     * 检查configKey是否存在
     *
     * @param configKey
     * @return
     * @throws Exception
     */
    void checkConfigKeyExists(String configKey) throws Exception;

}
