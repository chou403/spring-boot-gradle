package io.chou401.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.sys.SysUser;
import io.chou401.common.mapper.sys.SysUserMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysUserQuery;
import io.chou401.common.vo.sys.SysUserVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.framework.utils.PasswordUtil;
import io.chou401.framework.utils.TokenUtil;
import io.chou401.framework.utils.UUIDUtil;
import io.chou401.framework.utils.login.LoginUtil;
import io.chou401.system.dto.SysUserDto;
import io.chou401.system.dto.SysUserResetPasswordDto;
import io.chou401.system.dto.SysUserUpdatePasswordDto;
import io.chou401.system.dto.SysUserUpdateProfileDto;
import io.chou401.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统用户 服务实现类
 *
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final io.chou401.framework.service.LoginRedisService LoginRedisService;

    public SysUserServiceImpl(SysUserMapper sysUserMapper, io.chou401.framework.service.LoginRedisService loginRedisService) {
        this.sysUserMapper = sysUserMapper;
        LoginRedisService = loginRedisService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysUser(SysUserDto dto) throws Exception {
        checkUsernameExists(dto.getUsername());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(dto, sysUser);
        // 密码加盐
        String salt = RandomStringUtils.randomNumeric(6);
        sysUser.setSalt(salt);
        String password = PasswordUtil.encrypt(sysUser.getPassword(), salt);
        sysUser.setPassword(password);
        // 保存用户
        return save(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUserDto dto) throws Exception {
        Long id = dto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        BeanUtils.copyProperties(dto, sysUser);
        sysUser.setUpdateTime(new Date());
        return updateById(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        // 删除用户
        return removeById(id);
    }

    @Override
    public SysUserVo getSysUserById(Long id) throws Exception {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserVo> getSysUserPage(SysUserQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysUserVo> list = sysUserMapper.getSysUserPage(query);
        Paging<SysUserVo> paging = new Paging<>(list);
        return paging;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resetSysUserPassword(SysUserResetPasswordDto sysUserResetPasswordDto) throws Exception {
        Long userId = sysUserResetPasswordDto.getUserId();
        log.info("管理员重置用户密码：" + userId);
        SysUser sysUser = getById(userId);
        if (sysUser == null) {
            throw new BusinessException("系统用户不存在");
        }
        String password = sysUserResetPasswordDto.getPassword();
        return handleUpdatePassword(userId, password);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProfile(SysUserUpdateProfileDto sysUserUpdateProfileDto) throws Exception {
        Long id = sysUserUpdateProfileDto.getId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getNickname, sysUserUpdateProfileDto.getNickname());
        lambdaUpdateWrapper.set(SysUser::getPhone, sysUserUpdateProfileDto.getPhone());
        lambdaUpdateWrapper.set(SysUser::getEmail, sysUserUpdateProfileDto.getEmail());
        lambdaUpdateWrapper.set(SysUser::getGender, sysUserUpdateProfileDto.getGender());
        lambdaUpdateWrapper.set(SysUser::getHead, sysUserUpdateProfileDto.getHead());
        lambdaUpdateWrapper.eq(SysUser::getId, id);
        boolean flag = update(lambdaUpdateWrapper);
        // TODO 更新缓存中的用户信息
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePassword(SysUserUpdatePasswordDto sysUserUpdatePasswordDto) throws Exception {
        Long id = LoginUtil.getUserId();
        SysUser sysUser = getById(id);
        if (sysUser == null) {
            throw new BusinessException("用户信息不存在");
        }
        // 验证旧密码
        String dbPassword = sysUser.getPassword();
        String dbSalt = sysUser.getSalt();
        String oldPassword = sysUserUpdatePasswordDto.getOldPassword();
        String encryptOldPassword = PasswordUtil.encrypt(oldPassword, dbSalt);
        if (!dbPassword.equals(encryptOldPassword)) {
            throw new BusinessException("旧密码错误");
        }
        // 验证两次密码是否一致
        String password = sysUserUpdatePasswordDto.getPassword();
        String confirmPassword = sysUserUpdatePasswordDto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
        // 新密码不能与旧密码一致
        String newPassword = PasswordUtil.encrypt(password, dbSalt);
        if (dbPassword.equals(newPassword)) {
            throw new BusinessException("新密码不能与旧密码一致");
        }
        return handleUpdatePassword(id, password);
    }

    @Override
    public void checkUsernameExists(String username) throws Exception {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(username + "用户名已经存在");
        }
    }

    /**
     * 修改密码并删除该用户当前的登录信息
     *
     * @param id
     * @param password
     * @return
     * @throws Exception
     */
    private boolean handleUpdatePassword(Long id, String password) throws Exception {
        // 生产新的盐值
        String newSalt = UUIDUtil.getUuid();
        String newPassword = PasswordUtil.encrypt(password, newSalt);
        // 修改密码
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(SysUser::getPassword, newPassword);
        lambdaUpdateWrapper.set(SysUser::getSalt, newSalt);
        lambdaUpdateWrapper.set(SysUser::getUpdateTime, new Date());
        lambdaUpdateWrapper.eq(SysUser::getId, id);
        // 清除当前用户登录信息
        LoginRedisService.deleteLoginInfoByToken(TokenUtil.getToken());
        return update(lambdaUpdateWrapper);
    }

}
