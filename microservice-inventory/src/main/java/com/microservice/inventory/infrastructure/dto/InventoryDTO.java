package com.microservice.inventory.infrastructure.dto;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.microservice.inventory.domain.Inventory}
 */
public record InventoryDTO(UUID productId, Integer quantity) implements Serializable {
}