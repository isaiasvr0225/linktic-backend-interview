package com.microservice.product.infrastructure.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.microservice.product.domain.Product}
 */
@Builder
public record ProductResponseDTO(UUID id, String name, Double price) implements Serializable {
}