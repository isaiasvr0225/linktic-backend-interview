package com.microservice.product.infrastructure.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryAttributesDTO(UUID productId, Integer quantity) {}
