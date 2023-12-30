package io.chou401.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.chou401.common.entity.user.User;
import io.chou401.common.page.Paging;
import io.chou401.common.query.user.UserAppQuery;
import io.chou401.common.query.user.UserQuery;
import io.chou401.common.vo.user.UserAppVo;
import io.chou401.common.vo.user.UserVo;
import io.chou401.user.dto.UserDto;

/**
 * 用户信息 服务接口
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
public interface UserService extends IService<User> {

    /**
     * 根据微信openid获取用户
     *
     * @param openid
     * @return
     * @throws Exception
     */
    User getUserByOpenid(String openid) throws Exception;

    /**
     * 根据账号获取用户
     * @param username
     * @return
     * @throws Exception
     */
    User getUserByUsername(String username) throws Exception;

    /**
     * 添加用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean addUser(UserDto userDto) throws Exception;

    /**
     * 修改用户信息
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    boolean updateUser(UserDto userDto) throws Exception;

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteUser(Long id) throws Exception;

    /**
     * 用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserVo getUserById(Long id) throws Exception;

    /**
     * 用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserVo> getUserPage(UserQuery query) throws Exception;

    /**
     * App用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserAppVo getAppUserById(Long id) throws Exception;

    /**
     * App用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<UserAppVo> getAppUserPage(UserAppQuery query) throws Exception;

}
