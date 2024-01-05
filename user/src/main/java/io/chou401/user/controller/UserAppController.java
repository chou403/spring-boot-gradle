package io.chou401.user.controller;

import io.chou401.common.page.Paging;
import io.chou401.common.query.user.UserAppQuery;
import io.chou401.common.vo.user.UserAppVo;
import io.chou401.framework.response.ApiResult;
import io.chou401.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * App用户信息 控制器
 * {@code @author}  chou401
 * {@code @date} 2023-11-25
 */
@Slf4j
@RestController
@Tag(name = "App用户信息")
@RequestMapping("/app/user")
public class UserAppController {

    private final UserService userService;

    public UserAppController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取App用户信息详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppUser/{id}")
    @Operation(summary = "App用户信息详情")
    public ApiResult<UserAppVo> getAppUser(@PathVariable Long id) throws Exception {
        UserAppVo userAppVo = userService.getAppUserById(id);
        return ApiResult.success(userAppVo);
    }

    /**
     * App用户信息分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getAppUserPage")
    @Operation(summary = "App用户信息分页列表")
    public ApiResult<UserAppVo> getAppUserPage(@Valid @RequestBody UserAppQuery query) throws Exception {
        Paging<UserAppVo> paging = userService.getAppUserPage(query);
        return ApiResult.success(paging);
    }

}
