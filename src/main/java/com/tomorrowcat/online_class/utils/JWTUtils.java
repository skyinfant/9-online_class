package com.tomorrowcat.online_class.utils;

import com.tomorrowcat.online_class.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Jwt工具类   用于鉴权，登陆验证
 * 注意点:
 * 1、生成的token, 是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3、无法作废已颁布的token，除非改秘钥
 */
public class JWTUtils {

    /**
     * 过期时间，一周
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7L;

    /**
     * 加密秘钥
     */
    private static final String SECRET = "com.tomorrowcat888";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PRIFIX = "tomorrow";

    /**
     * subject
     */
    private static final String SUBJECT = "kim";

    /**
     * 根据用户信息，生成令牌
     *
     * @param user
     * @return
     */
    public static String generateJsonWebToken(User user) {

//        JWT格式组成 头部、负载、签名
//        header+payload+signature
//        头部：主要是描述签名算法
//        负载：主要描述是加密对象的信息，如⽤户的id等，也可以加些规范⾥⾯的东⻄，如iss
//        签发者， exp 过期时间， sub ⾯向的⽤户
//        签名：主要是把前⾯两部分进⾏加密，防⽌别⼈拿到token进⾏base解密后篡改token

        String token =
                //头部
                Jwts.builder().setSubject(SUBJECT)
                        //负载
                        .claim("id", user.getId())
                        .claim("name", user.getName())
                        .claim("pwd", user.getPwd())
                        .claim("head_img", user.getHeadImg())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                        //签名
                        .signWith(SignatureAlgorithm.ES256.HS256, SECRET).compact();

        token = TOKEN_PRIFIX + token;
        return token;

    }

    /**
     * 校验token的方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try{
            //claims里包含用户信息
            final Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PRIFIX, "")).getBody();

            return claims;
        }catch (Exception e){
            return null;
        }
    }


}















