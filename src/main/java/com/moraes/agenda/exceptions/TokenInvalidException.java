package com.moraes.agenda.exceptions;

public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException() {
    }

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
