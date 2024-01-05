package io.chou401.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.sys.SysUser;
import io.chou401.common.page.Paging;
import io.chou401.common.query.sys.SysUserQuery;
import io.chou401.common.vo.sys.SysUserVo;
import io.chou401.system.dto.SysUserDto;
import io.chou401.system.dto.SysUserResetPasswordDto;
import io.chou401.system.dto.SysUserUpdatePasswordDto;
import io.chou401.system.dto.SysUserUpdateProfileDto;

/**
 * 系统用户 服务接口
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 添加系统用户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysUser(SysUserDto dto) throws Exception;

    /**
     * 修改系统用户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysUser(SysUserDto dto) throws Exception;

    /**
     * 删除系统用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUser(Long id) throws Exception;

    /**
     * 系统用户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysUserVo getSysUserById(Long id) throws Exception;

    /**
     * 系统用户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysUserVo> getSysUserPage(SysUserQuery query) throws Exception;

    /**
     * 重置系统用户密码
     *
     * @param sysUserResetPasswordDto
     * @return
     * @throws Exception
     */
    boolean resetSysUserPassword(SysUserResetPasswordDto sysUserResetPasswordDto) throws Exception;

    /**
     * 修改个人信息
     *
     * @param sysUserUpdateProfileDto
     * @return
     * @throws Exception
     */
    boolean updateProfile(SysUserUpdateProfileDto sysUserUpdateProfileDto) throws Exception;

    /**
     * 修改系统用户密码
     *
     * @param sysUserUpdatePasswordDto
     * @return
     * @throws Exception
     */
    boolean updatePassword(SysUserUpdatePasswordDto sysUserUpdatePasswordDto) throws Exception;

    /**
     * 检查username是否存在
     *
     * @param username
     * @return
     * @throws Exception
     */
    void checkUsernameExists(String username) throws Exception;

}
