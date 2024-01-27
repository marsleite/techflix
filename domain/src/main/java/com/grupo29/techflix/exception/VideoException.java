package com.grupo29.techflix.exception;

public class VideoException extends RuntimeException {
    public VideoException(String message) {
        super(message);
    }

    public VideoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VideoException(Throwable cause) {
        super(cause);
    }
}
