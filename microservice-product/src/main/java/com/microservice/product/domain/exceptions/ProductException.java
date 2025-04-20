package com.microservice.product.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductException extends RuntimeException {

    private HttpStatus httpStatus;

    public ProductException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
