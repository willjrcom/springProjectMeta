package com.gazaltech.meta.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.domain.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO domainToDto(Address address);

    @Mapping(target = "id", ignore = true)
    Address createDtoToDomain(CreateAddressDTO client);

    @Mapping(target = "id", ignore = true)
    void updateDomainFromDto(UpdateAddressDTO dto, @MappingTarget Address client);
}
