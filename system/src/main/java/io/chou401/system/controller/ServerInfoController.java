package io.chou401.system.controller;

import cn.hutool.system.SystemUtil;
import io.chou401.framework.annotation.Permission;
import io.chou401.framework.bean.ProjectInfo;
import io.chou401.framework.bean.ServerInfo;
import io.chou401.framework.response.ApiResult;
import io.chou401.framework.utils.ServerInfoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/16
 **/
@Slf4j
@RestController
@Tag(name = "服务信息")
@RequestMapping("/admin/serverInfo")
public class ServerInfoController {

    @Autowired
    private Environment environment;

    /**
     * 获取服务信息详情
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/getServerInfo")
    @Operation(summary = "服务信息详情")
    @Permission("server:info")
    public ApiResult<ServerInfo> getServerInfo() throws Exception {
        ServerInfo serverInfo = ServerInfoUtil.getServerInfo();
        // 获取项目信息
        // 项目名称
        String name = environment.getProperty("spring.application.name");
        // 端口号
        String port = environment.getProperty("server.port");
        // 上下文路径
        String contextPath = environment.getProperty("server.servlet.context-path");
        // 激活的环境
        String active = environment.getProperty("spring.profiles.active");
        // 当前项目路径
        String userDir = SystemUtil.get("user.dir");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setName(name);
        projectInfo.setPort(port);
        projectInfo.setContextPath(contextPath);
        projectInfo.setActive(active);
        projectInfo.setUserDir(userDir);
        serverInfo.setProjectInfo(projectInfo);
        log.info("serverInfo:" + serverInfo);
        return ApiResult.success(serverInfo);
    }


}
