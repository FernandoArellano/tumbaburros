package com.eazybytes.cards.exception;

import com.eazybytes.cards.dto.ErrorResponseDto;
import jakarta.servlet.annotation.WebServlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AnotherGlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> specificError(ResourceNotFoundException ex, WebRequest request){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(null, null, null, null);
        errorResponseDto.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> global(Exception ex, WebRequest request){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(null, null, null, null);
        errorResponseDto.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
