package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysUser;
import io.chou401.common.query.sys.SysUserQuery;
import io.chou401.common.vo.sys.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户 Mapper 接口
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 系统用户详情
     *
     * @param id 用户 id
     * @return 用户详情
     */
    SysUserVo getSysUserById(Long id);

    /**
     * 系统用户分页列表
     *
     * @param query 参数
     * @return 用户列表
     */
    List<SysUserVo> getSysUserPage(SysUserQuery query);

    /**
     * 根据用户名获取登录用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    SysUser getSysUserByUsername(String username);

    /**
     * 根据角色ID获取用户数量
     *
     * @param roleId 角色 id
     * @return 用户数量
     */
    Integer getCountByRoleId(Long roleId);

}
