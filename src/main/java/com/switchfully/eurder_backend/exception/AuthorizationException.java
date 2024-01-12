package com.switchfully.eurder_backend.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String message) {
        super(message);
    }

}
