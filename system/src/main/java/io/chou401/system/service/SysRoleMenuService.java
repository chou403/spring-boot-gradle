package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysRoleMenu;

/**
 * 角色菜单关系表 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色ID删除角色权限关联关系
     *
     * @param roleId
     */
    void deleteSysRoleMenuByRoleId(Long roleId);

}
