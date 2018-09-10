package com.hello.world.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT Token
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
public class JWTToken implements AuthenticationToken {
  private String token;

  public JWTToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  public String getToken() {
    return token;
  }
}
