package com.ecomproject.ProductService.dto;

import org.springframework.http.HttpStatus;

public class ExceptionDto {
    private String message;
    private HttpStatus httpStatus;

    public ExceptionDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
