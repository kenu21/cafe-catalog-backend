package com.cafes.cafes.mappers;

import com.cafes.cafes.dto.AddressDtoResponse;
import com.cafes.cafes.entities.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { StreetMapper.class })
public interface AddressMapper {
    @Mapping(source = "streetEntity", target = "streetDtoResponse")
    AddressDtoResponse toDto(AddressEntity entity);
}
