package com.cafes.cafes.dto;

import com.cafes.cafes.entities.AddressEntity;
import com.cafes.cafes.entities.TagEntity;

import java.math.BigDecimal;
import java.util.List;

public record CafeWithTagsResponseDto(
        String name,
        String photoLink,
        Short priceRating,
        String openingHours,
        BigDecimal rating,
        Integer votesCount,
        AddressEntity addressEntity,
        List<TagEntity> tags
) {
}
