package hello.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String s) {
        super(s);
    }
}
