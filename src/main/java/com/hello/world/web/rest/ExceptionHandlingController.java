package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.exception.LoginFailedException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jarck-lou
 * @date 2018/12/28 10:49
 **/
@RestControllerAdvice
public class ExceptionHandlingController {
  private static Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

  /**
   * 捕获登录异常
   *
   * @param request 请求信息
   * @param ex 异常信息
   * @return ResponseBean
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(LoginFailedException.class)
  public ResponseBean handleLoginFailedException(HttpServletRequest request, Exception ex) {
    logger.error("Request: " + request.getRequestURL() + " raised " + ex);

    return new ResponseBean(CommonStatus.UNAUTHORIZED, ex.getMessage(), null);
  }

  /**
   * 捕捉shiro的异常
   *
   * @param ex 异常
   * @return ResponseBean
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(ShiroException.class)
  public ResponseBean handle401(ShiroException ex) {
    return new ResponseBean(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
  }

  /**
   * 捕捉UnauthorizedException
   *
   * @param ex 异常
   * @return ResponseBean
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseBean handle401(UnauthorizedException ex) {
    return new ResponseBean(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
  }

  /**
   * 捕捉其他所有异常
   *
   * @param request request
   * @param ex 异常
   * @return ResponseBean
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
    return new ResponseBean(getStatus(request).value(), ex.getMessage(), null);
  }

  /**
   * 获取Http状态
   *
   * @param request request
   * @return http status
   */
  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}
