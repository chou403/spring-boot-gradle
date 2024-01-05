package io.chou401.common.cache;

/**
 * 在当前线程中缓存token
 * 如果开启多线程需要获取
 * {@code @author}  chou401
 * {@code @date} 2023/12/7
 **/
public class TokenCache {

    /**
     * 当前线程中保存token值
     */
    private static final ThreadLocal<String> TOKEN_CACHE = new ThreadLocal<>();

    /**
     * 设置token值到当前线程中
     *
     * @param token token
     */
    public static void set(String token) {
        TOKEN_CACHE.set(token);
    }

    /**
     * 从当前线程获取token值
     *
     * @return token
     */
    public static String get() {
        return TOKEN_CACHE.get();
    }

    /**
     * 从当前线程中移除token值
     */
    public static void remove() {
        TOKEN_CACHE.remove();
    }

}
