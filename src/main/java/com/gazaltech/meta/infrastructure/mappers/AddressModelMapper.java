package com.gazaltech.meta.infrastructure.mappers;

import org.mapstruct.Mapper;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@Mapper(componentModel = "spring")
public interface AddressModelMapper {
    AddressModel domainToModel(Address address);

    Address modelToDomain(AddressModel client);
}
