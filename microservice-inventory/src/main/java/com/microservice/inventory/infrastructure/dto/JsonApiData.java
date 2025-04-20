package com.microservice.inventory.infrastructure.dto;

public record JsonApiData<T>(String type, String id, T attributes) {}
