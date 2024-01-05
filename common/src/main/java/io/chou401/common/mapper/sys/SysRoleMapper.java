package io.chou401.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.sys.SysRole;
import io.chou401.common.query.sys.SysRoleQuery;
import io.chou401.common.vo.sys.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色 Mapper 接口
 * {@code @author}  chou401
 * {@code @date} 2022-12-26
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 系统角色详情
     *
     * @param id 角色 id
     * @return 角色详情
     */
    SysRoleVo getSysRoleById(Long id);

    /**
     * 系统角色分页列表
     *
     * @param query 参数
     * @return 分页列表
     */
    List<SysRoleVo> getSysRolePage(SysRoleQuery query);

}
