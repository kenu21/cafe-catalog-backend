package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.TagDtoResponse;
import com.cafes.cafes.entities.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDtoResponse toDto(TagEntity entity);
}
