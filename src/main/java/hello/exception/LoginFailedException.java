package hello.exception;

/**
 * 登录异常
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
public class LoginFailedException extends RuntimeException {
  public LoginFailedException(String s) {
    super(s);
  }
}
