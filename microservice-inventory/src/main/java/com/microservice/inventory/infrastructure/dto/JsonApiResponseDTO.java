package com.microservice.inventory.infrastructure.dto;

public record JsonApiResponseDTO<T>(JsonApiData<T> data) {}
