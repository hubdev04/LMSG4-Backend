package com.ukg.authService.exceptions;

public class NoAuthorisationException extends Exception{
    public NoAuthorisationException(String message) {
        super(message);
    }
}
