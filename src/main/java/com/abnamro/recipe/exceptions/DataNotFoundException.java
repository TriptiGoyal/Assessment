package com.abnamro.recipe.exceptions;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends RuntimeException implements CustomException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
