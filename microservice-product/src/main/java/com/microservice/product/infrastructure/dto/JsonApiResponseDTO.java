package com.microservice.product.infrastructure.dto;

import lombok.Builder;

@Builder
public record JsonApiResponseDTO<T>(JsonApiData<T> data) {}
