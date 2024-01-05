package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysMenu;
import io.chou401.common.entity.sys.SysRole;
import io.chou401.common.entity.sys.SysRoleMenu;
import io.chou401.common.mapper.sys.SysRoleMapper;
import io.chou401.common.mapper.sys.SysUserMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysRoleQuery;
import io.chou401.common.vo.sys.SysRoleVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.system.dto.RoleMenusDto;
import io.chou401.system.dto.SysRoleDto;
import io.chou401.system.service.SysMenuService;
import io.chou401.system.service.SysRoleMenuService;
import io.chou401.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统角色 服务实现类
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    private final SysRoleMenuService sysRoleMenuService;

    private final SysMenuService sysMenuService;

    private final SysUserMapper sysUserMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysRoleMenuService sysRoleMenuService, SysMenuService sysMenuService, SysUserMapper sysUserMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysMenuService = sysMenuService;
        this.sysUserMapper = sysUserMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysRole(SysRoleDto dto) throws Exception {
        checkCodeExists(dto.getCode());
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(dto, sysRole);
        return save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRoleDto dto) throws Exception {
        Long id = dto.getId();
        SysRole sysRole = getById(id);
        if (sysRole == null) {
            throw new BusinessException("系统角色不存在");
        }
        // 只修改名称和备注
        sysRole.setName(dto.getName());
        sysRole.setRemark(dto.getRemark());
        sysRole.setUpdateTime(new Date());
        return updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) throws Exception {
        // 判断角色下是否存在用户，如果存在，则不能删除
        Integer count = sysUserMapper.getCountByRoleId(id);
        if (count > 0) {
            throw new BusinessException("该角色下还存在用户，不可删除");
        }
        return removeById(id);
    }

    @Override
    public SysRoleVo getSysRoleById(Long id) throws Exception {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRoleVo> getSysRolePage(SysRoleQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysRoleVo> list = sysRoleMapper.getSysRolePage(query);
        return new Paging<>(list);
    }

    @Override
    public List<SysRole> getSysRoleAllList() throws Exception {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(SysRole::getId);
        return list(lambdaQueryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setRoleMenus(RoleMenusDto roleMenusDto) throws Exception {
        Long roleId = roleMenusDto.getRoleId();
        List<Long> menuIds = roleMenusDto.getMenuIds();
        SysRole sysRole = getById(roleId);
        if (sysRole == null) {
            throw new BusinessException("角色不存在");
        }
        // 先删除角色权限关系
        sysRoleMenuService.deleteSysRoleMenuByRoleId(roleId);
        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        // 保存角色权限关系
        for (Long menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setChoice(true);
            sysRoleMenus.add(sysRoleMenu);
        }
        // 补全上级菜单父ID
        Set<Long> completionMenuIds = completionMenuParentIds(menuIds);
        if (CollectionUtils.isNotEmpty(completionMenuIds)) {
            for (Long completionMenuId : completionMenuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(completionMenuId);
                sysRoleMenu.setChoice(false);
                sysRoleMenus.add(sysRoleMenu);
            }
        }
        boolean flag = sysRoleMenuService.saveBatch(sysRoleMenus);
        if (!flag) {
            throw new BusinessException("设置角色权限异常");
        }
        return true;
    }

    @Override
    public void checkCodeExists(String code) throws Exception {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getCode, code);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(code + "角色编码已经存在");
        }
    }

    /**
     * 补全菜单父ID
     *
     * @param choiceMenuIds
     * @return
     */
    private Set<Long> completionMenuParentIds(List<Long> choiceMenuIds) {
        // 获取所有菜单
        List<SysMenu> menus = sysMenuService.list();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        Map<Long, SysMenu> menuMap = menus.stream().collect(Collectors.toMap(SysMenu::getId, SysMenu -> SysMenu));
        // 获取选择的菜单对象集合
        List<SysMenu> choiceMenus = new ArrayList<>();
        for (Long choiceMenuId : choiceMenuIds) {
            SysMenu sysMenu = menuMap.get(choiceMenuId);
            if (sysMenu != null) {
                choiceMenus.add(sysMenu);
            }
        }
        // 递归获取上级ID直到parentId为0截止
        if (CollectionUtils.isEmpty(choiceMenus)) {
            return null;
        }
        Set<Long> menuIdSet = new HashSet<>();
        for (SysMenu choiceMenu : choiceMenus) {
            recursionCompletionMenu(choiceMenuIds, menuIdSet, menuMap, choiceMenu);
        }
        return menuIdSet;
    }

    /**
     * 递归补全菜单ID集合
     *
     * @param choiceMenuIds
     * @param menuIdSet
     * @param menuMap
     * @param choiceMenu
     */
    private void recursionCompletionMenu(List<Long> choiceMenuIds, Set<Long> menuIdSet, Map<Long, SysMenu> menuMap, SysMenu choiceMenu) {
        Long parentId = choiceMenu.getParentId();
        // 判断是否存在此父ID，不存在则添加
        if (!choiceMenuIds.contains(parentId) && !menuIdSet.contains(parentId)) {
            if (parentId != 0) {
                menuIdSet.add(parentId);
                SysMenu parentMenu = menuMap.get(parentId);
                recursionCompletionMenu(choiceMenuIds, menuIdSet, menuMap, parentMenu);
            }
        }
    }

}
