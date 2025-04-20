package com.microservice.inventory.presentation.controller_advice;
import com.microservice.inventory.domain.exceptions.InventoryException;
import com.microservice.inventory.infrastructure.dto.InventoryErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.microservice.inventory.presentation.controller_advice
 * @name : InventoryControllerAdvice.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class InventoryControllerAdvice {

    /**
     * This method is used to handle InventoryException
     * @param inventoryException InventoryException
     * @return ResponseEntity<InventoryErrorDto>
     */
    @ExceptionHandler(value = InventoryException.class)
    public ResponseEntity<InventoryErrorDTO> handleProductException(InventoryException inventoryException) {
        return ResponseEntity.status(inventoryException.getHttpStatus()).body(InventoryErrorDTO.builder()
                .statusCode(inventoryException.getHttpStatus())
                .message(inventoryException.getMessage())
                .build());
    }
}
