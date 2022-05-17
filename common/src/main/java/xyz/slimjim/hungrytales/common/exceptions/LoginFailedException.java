package xyz.slimjim.hungrytales.common.exceptions;

public class LoginFailedException extends HungryTalesException {
    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
