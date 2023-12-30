package com.maoxian.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 *
 * @author Lin
 * @date 2023/9/21 15:30
 */
public class JwtUtil {

    /**
     * 有效期
     */
    public static final Long JWT_TTL = 7 * 24 * 60 * 60 * 1000L;

    /**
     * 密钥明文
     */
    public static final String JWT_KEY = "dGC/RXC5KfawGbGaRp4z";

    /**
     * 生成随机的字符串，作为JWT的唯一标识
     *
     * @return UUID唯一标识
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 创建JWT
     *
     * @param subject 主题
     * @return jwt
     */
    public static String createJwt(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUuid());
        return builder.compact();
    }

    /**
     * 创建JWT
     *
     * @param subject   主题
     * @param ttlMillis 过期时间
     * @return jwt
     */
    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUuid());
        return builder.compact();
    }

    /**
     * 创建JWT
     *
     * @param subject   主题
     * @param ttlMillis 过期时间
     * @param id        id
     * @return jwt
     */
    public static String createJwt(String subject, Long ttlMillis, String id) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 构建JWT
     *
     * @param subject  主题
     * @param ttlMills 过期时间
     * @param uuid     UUID唯一标识
     * @return JWT构建器
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMills, String uuid) {

        //JWT签名算法和密钥
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();

        //过期时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMills == null) {
            ttlMills = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMills;
        Date expDate = new Date(expMillis);

        return Jwts
                //jwt构建器
                .builder()
                //UUID唯一标识
                .setId(uuid)
                //主题，可以是JSON数据
                .setSubject(subject)
                //签发者
                .setIssuer("maoxian")
                //签发时间
                .setIssuedAt(now)
                //使用指定签名算法和密钥签名
                .signWith(signatureAlgorithm, secretKey)
                //设置过期时间
                .setExpiration(expDate);
    }

    /**
     * 生成加密后的密钥
     *
     * @return secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析jwt
     *
     * @param jwt jwt字符串
     * @return claims 声明信息
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts
                //jwt解析器
                .parser()
                //验证签名
                .setSigningKey(secretKey)
                //解析jwt，返回JWT的所有声明信息和签名
                .parseClaimsJws(jwt)
                //获取声明信息
                .getBody();
    }
}
