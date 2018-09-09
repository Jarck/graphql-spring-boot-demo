package hello.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hello.dto.result.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
public class JWTUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

  // 过期时间5min
  private static final long EXPIRE_TIME = 5 * 60 * 1000;

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
              .build();
      DecodedJWT jwt = verifier.verify(token);

      return true;
    } catch (Exception exception) {
      LOGGER.error("校验token失败", exception);
      return false;
    }
  }

  /**
   * 获取token中的用户id
   *
   * @param token
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
   * 获取token中的手机号码
   *
   * @param token
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
