package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysRole;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysRoleQuery;
import io.chou401.common.vo.sys.SysRoleVo;
import io.chou401.system.dto.RoleMenusDto;
import io.chou401.system.dto.SysRoleDto;

import java.util.List;

/**
 * 系统角色 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 添加系统角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysRole(SysRoleDto dto) throws Exception;

    /**
     * 修改系统角色
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysRole(SysRoleDto dto) throws Exception;

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Long id) throws Exception;

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleVo getSysRoleById(Long id) throws Exception;

    /**
     * 系统角色分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysRoleVo> getSysRolePage(SysRoleQuery query) throws Exception;

    /**
     * 获取所有角色列表
     *
     * @return
     * @throws Exception
     */
    List<SysRole> getSysRoleAllList() throws Exception;

    /**
     * 设置角色权限
     *
     * @param roleMenusDto
     * @return
     * @throws Exception
     */
    boolean setRoleMenus(RoleMenusDto roleMenusDto) throws Exception;

    /**
     * 检查code是否存在
     *
     * @param code
     * @return
     * @throws Exception
     */
    void checkCodeExists(String code) throws Exception;

}
