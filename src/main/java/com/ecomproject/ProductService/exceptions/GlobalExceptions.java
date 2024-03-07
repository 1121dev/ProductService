package com.ecomproject.ProductService.exceptions;

import com.ecomproject.ProductService.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException)
    {
        return new ResponseEntity<>(new ExceptionDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> handleBadRequestException(BadRequestException badRequestException)
    {
        return new ResponseEntity<>(new ExceptionDto(badRequestException.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
}
