package com.gazaltech.meta.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.infrastructure.models.AddressModel;
import com.gazaltech.meta.infrastructure.services.getCep.viaCep.ViaCepResponse;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    // Dto mapper
    AddressDTO domainToDto(Address address);

    @Mapping(target = "id", ignore = true)
    Address createDtoToDomain(CreateAddressDTO address);

    @Mapping(target = "id", ignore = true)
    void updateDomainFromDto(UpdateAddressDTO dto, @MappingTarget Address address);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "complement", ignore = true)
    @Mapping(target = "reference", ignore = true)
    @Mapping(target = "street", source = "logradouro")
    @Mapping(target = "neighborhood", source = "bairro")
    @Mapping(target = "city", source = "localidade")
    @Mapping(target = "zipCode", source = "cep")
    void updateDomainFromViaCepResponse(ViaCepResponse response, @MappingTarget Address address);

    // Model mapper
    AddressModel domainToModel(Address address);

    Address modelToDomain(AddressModel address);
}
