package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.exception.LoginFailedException;
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
}
