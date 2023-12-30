package io.chou401.login.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/12/27
 * {@code @description}
 */
@FeignClient(name = "system", url = "http://localhost:8092")
public interface SystemApi {

    @PostMapping("/admin/sysDict/getSysDictPage")
    String getSysDictLabelByValue(String dictCode, Serializable value);
}
