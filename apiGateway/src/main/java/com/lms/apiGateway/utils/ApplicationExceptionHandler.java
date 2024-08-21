package com.lms.apiGateway.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleGlobalException(RuntimeException ex) {
        ResponseDTO response = ResponseDTO.builder()
                .success(false)
                .message("Unauthenticated/unauthorized")
                .completionTimeStamp(LocalDateTime.now())
                .errorDetails(List.of(new ErrorDetail("401", ex.getMessage())))
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
