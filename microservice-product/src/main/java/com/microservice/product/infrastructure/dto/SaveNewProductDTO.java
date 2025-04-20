package com.microservice.product.infrastructure.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.microservice.product.domain.Product}
 */
public record SaveNewProductDTO(String name, Double price, Integer initialQuantity) implements Serializable {
}