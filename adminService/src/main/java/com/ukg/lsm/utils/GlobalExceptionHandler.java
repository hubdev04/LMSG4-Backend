package com.ukg.lsm.utils;

import com.ukg.lsm.exception.InvalidRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<?> handleInvalidRequestException(InvalidRequest exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
