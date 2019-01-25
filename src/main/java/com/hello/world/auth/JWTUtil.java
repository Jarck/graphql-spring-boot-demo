package com.hello.world.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hello.world.dto.result.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
public final class JWTUtil {
  // 过期时间1天
  private static final long EXPIRE_TIME = 60 * 60 * 1000 * 24;

  private JWTUtil() {
  }

  /**
   * 校验token是否正确
   *
   * @param token token
   * @param user  用户信息
   * @return 是否正确
   */
  public static boolean verify(String token, UserDto user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(user.getSecretKey());
      JWTVerifier verifier = JWT.require(algorithm)
              .withClaim("user_id", user.getId())
              .withClaim("name", user.getName())
              .withClaim("phone", user.getPhone())
              .withClaim("city_id", user.getCityId())
              .acceptExpiresAt(System.currentTimeMillis() + EXPIRE_TIME)
              .build();
      DecodedJWT jwt = verifier.verify(token);

      return true;
    } catch (TokenExpiredException ex) {
      log.error("token过期", ex);
      return false;
    } catch (Exception ex) {
      log.error("校验token失败", ex);
      return false;
    }
  }

  /**
   * 获取token中的用户id
   *
   * @param token token
   * @return token中包含的用户id
   */
  public static Integer getUserId(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);

      return jwt.getClaim("user_id").asInt();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 获取token中的用户名
   *
   * @param token token
   * @return token中包含的用户名
   */
  public static String getUserName(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);

      return jwt.getClaim("name").asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 获取token中的手机号码
   *
   * @param token token
   * @return token中包含的手机号码
   */
  public static String getPhone(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);

      return jwt.getClaim("phone").asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 获取token中的城市id
   *
   * @param token token
   * @return token中包含的城市id
   */
  public static Integer getCityId(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);

      return jwt.getClaim("city_id").asInt();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  /**
   * 生成签名, 5min过期
   *
   * @param user 用户信息
   * @return 加密的token
   */
  public static String sign(UserDto user) {
    try {
      Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
      Algorithm algorithm = Algorithm.HMAC256(user.getSecretKey());

      return JWT.create()
              .withClaim("user_id", user.getId())
              .withClaim("name", user.getName())
              .withClaim("phone", user.getPhone())
              .withClaim("city_id", user.getCityId())
              .withExpiresAt(date)
              .sign(algorithm);
    } catch (UnsupportedEncodingException exception) {
      return null;
    }
  }
}
