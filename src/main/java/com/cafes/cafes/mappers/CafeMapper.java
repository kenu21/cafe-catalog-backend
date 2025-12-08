package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.CafeDtoResponse;
import com.cafes.cafes.dto.CafeWithTagsResponseDto;
import com.cafes.cafes.entities.CafeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CafeMapper {
    CafeDtoResponse toDto(CafeEntity cafe);
    CafeWithTagsResponseDto toWithTagsResponseDto(CafeEntity cafe);
}
