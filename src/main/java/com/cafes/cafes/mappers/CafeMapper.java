package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.CafeDtoResponse;
import com.cafes.cafes.dto.CafeWithTagsResponseDto;
import com.cafes.cafes.entities.CafeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AddressMapper.class, TagMapper.class })
public interface CafeMapper {
    @Mapping(source = "addressEntity", target = "addressDtoResponse")
    CafeDtoResponse toDto(CafeEntity cafe);
    @Mapping(source = "addressEntity", target = "addressDtoResponse")
    CafeWithTagsResponseDto toWithTagsResponseDto(CafeEntity cafe);
}
