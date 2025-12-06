package com.cafes.cafes.dto;

import com.cafes.cafes.entities.AddressEntity;

import java.math.BigDecimal;

public record CafeDtoResponse(
        String name,
        String photoLink,
        Short priceRating,
        String openingHours,
        BigDecimal rating,
        Integer votesCount,
        AddressEntity addressEntity
) {
}
