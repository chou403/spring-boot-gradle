package io.chou401.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.chou401.common.entity.user.User;
import io.chou401.common.mapper.user.UserMapper;
import io.chou401.common.page.OrderByItem;
import io.chou401.common.page.Paging;
import io.chou401.common.query.user.UserAppQuery;
import io.chou401.common.query.user.UserQuery;
import io.chou401.common.vo.user.UserAppVo;
import io.chou401.common.vo.user.UserVo;
import io.chou401.framework.exception.BusinessException;
import io.chou401.framework.utils.PagingUtil;
import io.chou401.user.dto.UserDto;
import io.chou401.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户信息 服务实现类
 *
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByOpenid(String openid) throws Exception {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenid, openid);
        return getOne(wrapper);
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(UserDto userDto) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDto userDto) throws Exception {
        Long id = userDto.getId();
        User user = getById(id);
        if (user == null) {
            throw new BusinessException("用户信息不存在");
        }
        BeanUtils.copyProperties(userDto, user);
        user.setUpdateTime(new Date());
        return updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public UserVo getUserById(Long id) throws Exception {
        return userMapper.getUserById(id);
    }

    @Override
    public Paging<UserVo> getUserPage(UserQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<UserVo> list = userMapper.getUserPage(query);
        Paging<UserVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public UserAppVo getAppUserById(Long id) throws Exception {
        return userMapper.getAppUserById(id);
    }

    @Override
    public Paging<UserAppVo> getAppUserPage(UserAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<UserAppVo> list = userMapper.getAppUserPage(query);
        Paging<UserAppVo> paging = new Paging<>(list);
        return paging;
    }

}
