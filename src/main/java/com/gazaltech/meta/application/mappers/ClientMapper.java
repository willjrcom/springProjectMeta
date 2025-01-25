package com.gazaltech.meta.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.domain.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO domainToDto(Client client);
    
    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "address", ignore = true)
    Client createDtoToDomain(CreateClientDTO client);

    @Mapping(target = "id", ignore = true) 
    void updateDomainFromDto(UpdateClientDTO dto, @MappingTarget Client client);
}
