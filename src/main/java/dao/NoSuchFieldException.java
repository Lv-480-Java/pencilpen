package dao;

public class NoSuchFieldException extends RuntimeException {

    public NoSuchFieldException() {
        super();
    }

    public NoSuchFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchFieldException(String message) {
        super(message);
    }
}
