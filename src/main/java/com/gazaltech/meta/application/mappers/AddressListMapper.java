package com.gazaltech.meta.application.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface AddressListMapper {
    List<Address> modelsToDomains(List<AddressModel> models);
    List<AddressDTO> domainsToDtos(List<Address> domains);
}
