package com.microservice.product.infrastructure.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.microservice.product.domain.Product}
 */
@Builder
public record ProductResponseDTO(UUID id, String name, BigDecimal price) implements Serializable {
}