package com.microservice.inventory.infrastructure.dto;

import lombok.Builder;

@Builder
public record JsonApiData<T>(String type, String id, T attributes) {}
