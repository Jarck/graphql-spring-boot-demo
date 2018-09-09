package hello.web;

import hello.exception.LoginFailedException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionController {

  /**
   * 捕获Shiro异常
   *
   * @param e exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({ShiroException.class})
  public String handle401(ShiroException e) {
    log.error(e.getMessage());
    return "没有操作权限";
  }

  /**
   * UnauthenticatedException
   *
   * @param e exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({UnauthenticatedException.class})
  public String handle403(UnauthenticatedException e) {
    log.error(e.getMessage());
    return "需要登录才能访问";
  }

  /**
   * LoginFailedException
   *
   * @param ex exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({LoginFailedException.class})
  public String handleLoginFailed(Throwable ex) {
    log.error(ex.getMessage(), ex);
    return ex.getMessage();
  }

  /**
   * AuthenticationException
   *
   * @param ex exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({AuthenticationException.class})
  public String handle401(Throwable ex) {
    log.error(ex.getMessage(), ex);
    return ex.getMessage();
  }

  /**
   * UnauthorizedException
   *
   * @param ex exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({UnauthorizedException.class})
  public String handle400(Throwable ex) {
    log.error(ex.getMessage(), ex);
    return ex.getMessage();
  }

  /**
   * NotFoundException, NullPointerException
   *
   * @param ex exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class, NullPointerException.class})
  public String handle404(Throwable ex) {
    log.error(ex.getMessage(), ex);
    return "没有找到对应的数据";
  }

  /**
   * 参数校验失败异常处理
   *
   * @param request request
   * @param ex      exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({DuplicateKeyException.class})
  public String bindException(HttpServletRequest request, Throwable ex) {
    log.info(ex.getMessage());
    return "参数错误";
  }

  /**
   * 捕获其它异常
   *
   * @param request request
   * @param ex      exception
   * @return 异常消息
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public String globalException(HttpServletRequest request, Throwable ex) {
    log.error(ex.getMessage(), ex);
    return "系统异常";
  }


}
