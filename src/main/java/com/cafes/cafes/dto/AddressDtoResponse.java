package com.cafes.cafes.dto;

public record AddressDtoResponse(
        StreetDtoResponse streetDtoResponse,
        String buildingNumber
) {
}
