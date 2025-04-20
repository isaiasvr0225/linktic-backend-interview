package com.microservice.product.presentation.controller_advice;
import com.microservice.product.domain.exceptions.ProductException;
import com.microservice.product.infrastructure.dto.ProductErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.microservice.product.presentation.controller_advice
 * @name : ProductControllerAdvice.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class ProductControllerAdvice {

    /**
     * This method is used to handle ProductException
     * @param productException productException
     * @return ResponseEntity<ProductErrorDto>
     */
    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ProductErrorDTO> handleProductException(ProductException productException) {
        return ResponseEntity.status(productException.getHttpStatus()).body(ProductErrorDTO.builder()
                .statusCode(productException.getHttpStatus())
                .message(productException.getMessage())
                .build());
    }
}
