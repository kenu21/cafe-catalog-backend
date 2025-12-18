package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.PhotoDtoResponse;
import com.cafes.cafes.entities.PhotoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoDtoResponse toDto(PhotoEntity photoEntity);
}
