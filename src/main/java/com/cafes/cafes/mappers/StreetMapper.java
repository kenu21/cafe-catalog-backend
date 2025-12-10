package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.StreetDtoResponse;
import com.cafes.cafes.entities.StreetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CityMapper.class })
public interface StreetMapper {
    @Mapping(source = "cityEntity", target = "cityDtoResponse")
    StreetDtoResponse toDto(StreetEntity streetEntity);
}
