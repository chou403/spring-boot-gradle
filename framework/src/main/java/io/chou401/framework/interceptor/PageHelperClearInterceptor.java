package io.chou401.framework.interceptor;

import com.github.pagehelper.PageHelper;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/10/12
 **/
public class PageHelperClearInterceptor extends BaseMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        try {
            PageHelper.clearPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
