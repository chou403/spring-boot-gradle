package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysMenu;
import io.chou401.common.query.sys.SysMenuQuery;
import io.chou401.common.vo.sys.SysMenuTreeVo;
import io.chou401.common.vo.sys.SysMenuVo;
import io.chou401.common.vo.sys.SysNavMenuTreeVo;
import io.chou401.system.dto.SysMenuDto;

import java.util.List;

/**
 * 系统菜单 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 添加系统菜单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysMenu(SysMenuDto dto) throws Exception;

    /**
     * 修改系统菜单
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysMenu(SysMenuDto dto) throws Exception;

    /**
     * 删除系统菜单
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysMenu(Long id) throws Exception;

    /**
     * 系统菜单详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysMenuVo getSysMenuById(Long id) throws Exception;

    /**
     * 获取所有的系统菜单树形列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getAllSysMenuTreeList(SysMenuQuery query) throws Exception;

    /**
     * 获取启用的系统菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysMenuTreeVo> getSysMenuTreeList() throws Exception;

    /**
     * 获取当前登录用户导航菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysNavMenuTreeVo> getNavMenuTreeList() throws Exception;

    /**
     * 获取角色权限ID集合
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getMenuIdsByRoleId(Long roleId) throws Exception;

    /**
     * 检查code是否存在
     *
     * @param code
     * @return
     * @throws Exception
     */
    void checkCodeExists(String code) throws Exception;

}
