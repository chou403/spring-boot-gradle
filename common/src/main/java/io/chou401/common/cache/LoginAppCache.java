package io.chou401.common.cache;


import io.chou401.common.vo.login.LoginAppVo;

/**
 * 在当前线程中缓存token
 * 如果开启多线程需要获取
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 **/
public class LoginAppCache {

    /**
     * 当前线程中保存APP移动端登录信息
     */
    private static final ThreadLocal<LoginAppVo> APP_LOGIN_CACHE = new ThreadLocal<>();

    /**
     * 设置APP移动端登录信息到当前线程中
     *
     * @param loginAppVo 登录信息
     */
    public static void set(LoginAppVo loginAppVo) {
        APP_LOGIN_CACHE.set(loginAppVo);
    }

    /**
     * 从当前线程获取APP移动端登录信息
     *
     * @return 登录信息
     */
    public static LoginAppVo get() {
        return APP_LOGIN_CACHE.get();
    }

    /**
     * 从当前线程中移除APP移动端登录信息
     */
    public static void remove() {
        APP_LOGIN_CACHE.remove();
    }

}
