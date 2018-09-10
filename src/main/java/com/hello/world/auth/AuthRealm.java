package com.hello.world.auth;

import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import com.hello.world.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户鉴权
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
public class AuthRealm extends AuthorizingRealm {
  @Resource
  private IUserService userService;

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  /**
   * 使用JWT替代原生Token
   *
   * @param token
   * @return
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JWTToken;
  }

  /**
   * 校验用户身份
   *
   * @param auth
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    String token = (String) auth.getCredentials();

    // 获取token中的手机号码
    String phone = JWTUtil.getPhone(token);
    if (phone == null) {
      throw new AuthenticationException("Token无效");
    }

    UserDto user = userService.searchWithPhone(phone);
    if (user == null) {
      throw new AuthenticationException("用户不存在");
    }

    String redisToken = stringRedisTemplate.opsForValue().get(phone + "_token");
    if (null == redisToken || !token.equals(redisToken)) {
      throw new AuthenticationException("Tokens已失效");
    } else {
      // 自动续命1天
      stringRedisTemplate.opsForValue().set(phone + "_token", token, 1, TimeUnit.DAYS);
    }

    if (!JWTUtil.verify(token, user)) {
      throw new AuthenticationException("Token无效");
    }

    return new SimpleAuthenticationInfo(token, token, "auth_realm");
  }

  /**
   * Authorizaton 授权
   *
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String phone = JWTUtil.getPhone(principals.toString());

    UserDto user = userService.searchWithPhone(phone);
    if (user == null) {
      return null;
    }

    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

    List<Role> roles = user.getRoles();
    authorizationInfo.addRoles(roles.stream().map(Role::getName).collect(Collectors.toList()));

    List<Permission> permissions = user.getPermissions();
    for (Permission permission : permissions) {
      authorizationInfo.addStringPermission(permission.getName());
    }

    return authorizationInfo;
  }
}
