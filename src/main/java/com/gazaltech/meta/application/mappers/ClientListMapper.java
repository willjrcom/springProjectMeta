package com.gazaltech.meta.application.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.domain.Client;

@Mapper(componentModel = "spring", uses = { ClientMapper.class })
public interface ClientListMapper {
    List<ClientDTO> domainsToDtos(List<Client> dClientDTOtos);
}
