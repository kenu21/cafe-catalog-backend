package com.cafes.cafes.dto;

import java.math.BigDecimal;

public record CafeDtoResponse(
        Long id,
        String name,
        PhotoDtoResponse mainPhoto,
        Short priceRating,
        String openingHours,
        BigDecimal rating,
        Integer votesCount,
        AddressDtoResponse addressDtoResponse
) {
}
