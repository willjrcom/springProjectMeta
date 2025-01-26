package com.gazaltech.meta.application.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.domain.Address;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface AddressListMapper {
    List<AddressDTO> domainsToDtos(List<Address> dtos);

}
