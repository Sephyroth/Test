package com.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.test.domain.City;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

	public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	public abstract City toCity(City city);
}
