package io.chou401.common.cache;


import io.chou401.common.vo.login.LoginVo;

/**
 * 在当前线程中缓存管理后台登录信息
 * 如果开启多线程需要获取
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 **/
public class LoginCache {

    /**
     * 当前线程中保存管理后台登录信息
     */
    private static final ThreadLocal<LoginVo> LOGIN_CACHE = new ThreadLocal<>();

    /**
     * 设置管理后台登录信息到当前线程中
     *
     * @param loginVo 登录信息
     */
    public static void set(LoginVo loginVo) {
        LOGIN_CACHE.set(loginVo);
    }

    /**
     * 从当前线程获取管理后台登录信息
     *
     * @return 登录信息
     */
    public static LoginVo get() {
        return LOGIN_CACHE.get();
    }

    /**
     * 从当前线程中移除管理后台登录信息
     */
    public static void remove() {
        LOGIN_CACHE.remove();
    }

}
