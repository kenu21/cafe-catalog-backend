package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.CityDtoResponse;
import com.cafes.cafes.entities.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityDtoResponse toDto(CityEntity cityEntity);
}
