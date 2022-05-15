package xyz.slimjim.hungrytales.common.exceptions;

public class HungryTalesException extends RuntimeException {

    public HungryTalesException(String message) {
        super(message);
    }

    public HungryTalesException(String message, Throwable cause) {
        super(message, cause);
    }
}
