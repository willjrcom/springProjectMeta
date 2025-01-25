package com.gazaltech.meta.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.domain.Client;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO domainToDto(Client address);

    Client dtoToDomain(ClientDTO dto);

    @Mapping(target = "address", ignore = true)
    Client createDtoToDomain(CreateClientDTO client);

    @Mapping(target = "id", ignore = true) 
    void updateDomainFromDto(UpdateClientDTO dto, @MappingTarget Client client);
}
