package io.chou401.framework.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.chou401.framework.exception.NotAuthException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtil {

    @Value("${login.admin.token-expire-minutes}")
    private static int expireDate;

    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    /**
     * 生成jwt
     */
    public static String createJwt(Map<String, String> map) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        // 获取日历对象
        Calendar nowTime = Calendar.getInstance();
        // 默认过期时间
        nowTime.add(Calendar.MINUTE, expireDate);
        // 得到过期时间
        Date expireTime = nowTime.getTime();
        // 设置header信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // 新建一个 JWT 的 Builder 对象
        JWTCreator.Builder builder = JWT.create();
        // 将map集合中的数据设置进payload
        map.forEach(builder::withClaim);

        // 生成 token：头部+载荷+签名
        return builder.withHeader(header).withExpiresAt(expireTime).sign(algorithm);
    }

    public static boolean checkToken(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证Token
     *
     * @param token token 值
     */
    private static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new NotAuthException("令牌无效，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     *
     * @param token token值
     */
    public static Map<String, Claim> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }


}
