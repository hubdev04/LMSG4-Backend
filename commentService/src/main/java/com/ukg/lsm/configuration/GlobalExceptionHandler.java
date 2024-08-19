package com.ukg.lsm.configuration;

import com.ukg.lsm.dtos.ErrorDetail;
import com.ukg.lsm.dtos.ResponseDTO;
import com.ukg.lsm.exception.InvalidRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<ResponseDTO> handleInvalidRequestException(InvalidRequest ex) {
        List<ErrorDetail> errorDetails = Collections.singletonList(new ErrorDetail(ex.getMessage()));

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(false)
                .message("Failed to process request")
                .completionTimeStamp(LocalDateTime.now())
                .result(null)
                .errorDetails(errorDetails)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
