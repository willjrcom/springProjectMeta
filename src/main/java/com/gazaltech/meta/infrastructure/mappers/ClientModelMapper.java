package com.gazaltech.meta.infrastructure.mappers;

import org.mapstruct.Mapper;
import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.infrastructure.models.ClientModel;

@Mapper(componentModel = "spring")
public interface ClientModelMapper {
    ClientModel domainToModel(Client address);

    Client modelToDomain(ClientModel client);
}
