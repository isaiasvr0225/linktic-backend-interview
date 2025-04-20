package com.microservice.inventory.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InventoryException extends RuntimeException {

    private HttpStatus httpStatus;

    public InventoryException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
