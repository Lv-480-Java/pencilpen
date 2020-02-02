package domain.exception.registration;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException() {
        super();
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}