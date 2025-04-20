package com.microservice.product.infrastructure.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * This is a DTO class for Product error handling
 * @package : com.microservice.inventory.infrastructure.dto
 * @name : ProductErrorDto.java
 * @date : 2025-04-19
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@Builder
public record ProductErrorDTO(
        HttpStatus statusCode,
        String message
) implements Serializable {}
