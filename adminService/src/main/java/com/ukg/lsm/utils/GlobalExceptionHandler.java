package com.ukg.lsm.utils;

import com.ukg.lsm.exception.InvalidRequest;
import com.ukg.lsm.exception.NoAuthorisationException;
import com.ukg.lsm.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<ResponseDTO> handleInvalidRequestException(InvalidRequest exception){
        List<ErrorDetail> errorDetails = Collections.singletonList(new ErrorDetail(HttpStatus.BAD_REQUEST.toString(), exception.getMessage()));

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(false)
                .message("Invalid request")
                .completionTimeStamp(LocalDateTime.now())
                .errorDetails(errorDetails)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoAuthorisationException.class)
    public ResponseEntity<ResponseDTO> handleNoAuthorisationException(NoAuthorisationException exception){
        List<ErrorDetail> errorDetails = Collections.singletonList(new ErrorDetail(HttpStatus.UNAUTHORIZED.toString(), exception.getMessage()));

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(false)
                .message("Unauthorized")
                .completionTimeStamp(LocalDateTime.now())
                .errorDetails(errorDetails)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleResourceNotFoundException(NoAuthorisationException exception){
        List<ErrorDetail> errorDetails = Collections.singletonList(new ErrorDetail(HttpStatus.NOT_FOUND.toString(), exception.getMessage()));

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(false)
                .message("Resource not found")
                .completionTimeStamp(LocalDateTime.now())
                .errorDetails(errorDetails)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
