package com.github.whvixd.demo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Q:使用JWT作为安全传输，可以不使用https了吗？
 * A:这是两种不同领区的安全认证机制，可以使用https+JWT的方式，可以将JWT当作访问服务器的令牌，与https的使用不冲突。
 * <p>
 * Created by wangzhixiang on 2022/04/10.
 */
public class JWTDemo {

    // KMS中保存
    private static final String sign = "ewqhe1ue89wqe02!!1*&";

    // 浏览器访问
    public static String getToken(String username, String userId) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        // 头部，记录元信息，如加密算法
        Map<String, Object> h = new HashMap<>();
        h.put("alg", "HMAC256");// 签名使用的算法
        h.put("type", "JWT");// 令牌类型

        // 生成sign
        return JWT.create().withHeader(h)
                // 用户信息，可以作为传输过程中的敏感信息的加密方式
                .withClaim("username", username)
                .withClaim("user_id", userId)

                // 过期时间
                .withExpiresAt(calendar.getTime())
                // 密钥
                .sign(Algorithm.HMAC256(sign));

    }

    public static boolean checkToken(String token, String username, String userId) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sign)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        String header = verify.getHeader();
        System.out.println(header);

        // 也可以后端直接拿这些敏感信息直接使用
        String verifyUsername = verify.getClaim("username").asString();
        if (!verifyUsername.equals(username)) {
            return false;
        }

        String verifyUserId = verify.getClaim("user_id").asString();
        if (!verifyUserId.equals(userId)) {
            return false;
        }

        return true;

    }

    public static void main(String[] args) {
        String username = "whvixd";
        String userId = "1234";
        String token = getToken(username, userId);
        boolean b = checkToken(token, username, "12345");
        System.out.println(b);
    }


}
