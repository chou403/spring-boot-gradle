package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysUser;
import io.chou401.common.query.sys.SysUserQuery;
import io.chou401.common.vo.sys.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户 Mapper 接口
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 系统用户详情
     *
     * @param id
     * @return
     */
    SysUserVo getSysUserById(Long id);

    /**
     * 系统用户分页列表
     *
     * @param query
     * @return
     */
    List<SysUserVo> getSysUserPage(SysUserQuery query);

    /**
     * 根据用户名获取登录用户对象
     *
     * @param username
     * @return
     */
    SysUser getSysUserByUsername(String username);

    /**
     * 根据角色ID获取用户数量
     *
     * @param roleId
     * @return
     */
    Integer getCountByRoleId(Long roleId);

}
