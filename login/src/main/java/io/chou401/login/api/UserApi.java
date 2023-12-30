package io.chou401.login.api;

import io.chou401.common.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/27
 * {@code @description}
 */
@FeignClient(name = "user", url = "http://localhost:8093")
public interface UserApi {

    @PostMapping("/admin/user/feign/getUserByOpenid")
    User getUserByOpenid(String openid);

    @PostMapping("/admin/user/feign/addUser")
    Boolean save(User user);

    @PostMapping("/admin/user/feign/getUser")
    User getById(Long id);

    @PostMapping("/admin/user/feign/getUserByUsername")
    User getUserByUsername(String username);

    @PostMapping("/admin/user/updateUser")
    String updateById(User user);
}
