package io.chou401.framework.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/4/13
 **/
@Slf4j
@RestControllerAdvice
public class GlobalStringTrimHandler {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 去掉表单请求参数中字符串的前后空格
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
