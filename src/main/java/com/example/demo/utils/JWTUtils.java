package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTUtils {

    /**
     * 公钥
     */
    private static String SECRET = "qiang";
    public static String createToken(int id, String username,
                                      String type) throws Exception {
        // 签发时间
        Date iatDate = new Date();

        // 过期时间，7天时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 24 * 7);
        Date experiesDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", id)
                .withClaim("username", username)
                .withClaim("type", type)
                // 设置过期的日期
                .withExpiresAt(experiesDate)
                // 签发时间
                .withIssuedAt(iatDate)
                // 加密
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 解密
     */

    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {

            throw new Exception("登录过期");
        }
        return jwt.getClaims();
    }
}
