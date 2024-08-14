package com.ukg.api_gateway.exceptions;

public class NoAuthorisationException extends Exception{
    public NoAuthorisationException(String message) {
        super(message);
    }
}
