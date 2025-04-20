package com.microservice.inventory.infrastructure.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;


@Builder
public record ProductResponseDTO(UUID id, String name, Double price) implements Serializable {
}