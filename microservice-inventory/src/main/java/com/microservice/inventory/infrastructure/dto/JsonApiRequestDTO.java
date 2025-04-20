package com.microservice.inventory.infrastructure.dto;

public record JsonApiRequestDTO<T>(JsonApiData<T> data) {}
