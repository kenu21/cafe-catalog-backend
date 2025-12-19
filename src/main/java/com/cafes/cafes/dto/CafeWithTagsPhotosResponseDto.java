package com.cafes.cafes.dto;

import java.math.BigDecimal;
import java.util.List;

public record CafeWithTagsPhotosResponseDto(
        String name,
        PhotoDtoResponse mainPhoto,
        List<PhotoDtoResponse> photos,
        Short priceRating,
        String openingHours,
        BigDecimal rating,
        Integer votesCount,
        AddressDtoResponse addressDtoResponse,
        List<TagDtoResponse> tags
) {
}
