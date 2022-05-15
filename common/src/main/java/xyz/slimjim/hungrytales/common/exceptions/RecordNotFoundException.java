package xyz.slimjim.hungrytales.common.exceptions;

public class RecordNotFoundException extends HungryTalesException {

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
