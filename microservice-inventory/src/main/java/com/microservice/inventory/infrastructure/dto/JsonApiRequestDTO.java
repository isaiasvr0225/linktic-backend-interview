package com.microservice.inventory.infrastructure.dto;

import lombok.Builder;

@Builder
public record JsonApiRequestDTO<T>(JsonApiData<T> data) {}
