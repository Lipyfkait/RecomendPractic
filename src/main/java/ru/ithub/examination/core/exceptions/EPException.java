package ru.ithub.examination.core.exceptions;

public class EPException extends RuntimeException{
    public EPException() {
    }

    public EPException(String message) {
        super(message);
    }

    public EPException(String message, Throwable cause) {
        super(message, cause);
    }

    public EPException(Throwable cause) {
        super(cause);
    }

    public EPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
