package com.microservice.inventory.infrastructure.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryAttributesDTO(UUID productId, Integer quantity) {}
