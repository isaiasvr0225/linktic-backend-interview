package com.microservice.product.infrastructure.dto;

import lombok.Builder;

@Builder
public record JsonApiData<T>(String type, String id, T attributes) {}
