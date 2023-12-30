package io.chou401.common.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.chou401.common.entity.user.User;
import io.chou401.common.query.user.UserAppQuery;
import io.chou401.common.query.user.UserQuery;
import io.chou401.common.vo.user.UserAppVo;
import io.chou401.common.vo.user.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息 Mapper 接口
 * <p>
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户信息详情
     *
     * @param id
     * @return
     */
    UserVo getUserById(Long id);

    /**
     * 用户信息分页列表
     *
     * @param query
     * @return
     */
    List<UserVo> getUserPage(UserQuery query);

    /**
     * App用户信息详情
     *
     * @param id
     * @return
     */
    UserAppVo getAppUserById(Long id);

    /**
     * App用户信息分页列表
     *
     * @param query
     * @return
     */
    List<UserAppVo> getAppUserPage(UserAppQuery query);

}
