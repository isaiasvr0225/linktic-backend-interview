package com.microservice.product.infrastructure.dto;

import lombok.Builder;

@Builder
public record JsonApiRequestDTO<T>(JsonApiData<T> data) {}
