package com.cafes.cafes.dto;

public record StreetDtoResponse(
        String name,
        CityDtoResponse cityDtoResponse
) {
}
