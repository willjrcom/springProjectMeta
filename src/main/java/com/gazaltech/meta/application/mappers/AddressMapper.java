package com.gazaltech.meta.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.domain.Address;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "id", target = "id")
    AddressDTO domainToDto(Address address);

    @Mapping(source = "id", target = "id")
    Address dtoToDomain(AddressDTO dto);

    @Mapping(source = "id", target = "id")
    Address createDtoToDomain(CreateAddressDTO client);

    @Mapping(source = "id", target = "id")
    Address updateDtoToDomain(UpdateAddressDTO client);
}
