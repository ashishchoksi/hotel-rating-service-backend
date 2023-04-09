package com.example.hotel.service.exceptions;

import com.example.hotel.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse response = ApiResponse.builder()
                .message(ex.getMessage())
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
