package com.microservice.inventory.infrastructure.dto;

import java.util.UUID;


public record InventoryAttributesDTO(UUID productId, Integer quantity) {}
