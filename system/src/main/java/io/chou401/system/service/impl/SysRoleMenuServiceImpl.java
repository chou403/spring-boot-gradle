package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysRoleMenu;
import io.chou401.common.mapper.sys.SysRoleMenuMapper;
import io.chou401.system.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色菜单关系表 服务实现类
 *
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    public SysRoleMenuServiceImpl(SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    @Override
    public void deleteSysRoleMenuByRoleId(Long roleId) {
        LambdaUpdateWrapper<SysRoleMenu> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SysRoleMenu::getRoleId, roleId);
        remove(lambdaUpdateWrapper);
    }

}
