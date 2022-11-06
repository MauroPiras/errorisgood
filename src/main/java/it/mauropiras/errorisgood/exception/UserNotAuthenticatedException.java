package it.mauropiras.errorisgood.exception;

public class UserNotAuthenticatedException extends CustomException {

    public UserNotAuthenticatedException() {
        super();
    }

    public UserNotAuthenticatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserNotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuthenticatedException(String message) {
        super(message);
    }

    public UserNotAuthenticatedException(Throwable cause) {
        super(cause);
    }
}
