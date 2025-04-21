package com.microservice.product.infrastructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.microservice.product.domain.Product}
 */
public record SaveNewProductDTO(String name, BigDecimal price, Integer initialQuantity) implements Serializable {
}