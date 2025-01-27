package com.gazaltech.meta.factories;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.infrastructure.models.ClientModel;

public class ClientFactory {

    public static CreateClientDTO createClientDTO = CreateClientDTO.builder()
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    public static UpdateClientDTO updateClientDTO = UpdateClientDTO.builder()
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    public static ClientDTO clientDTO = ClientDTO.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    public static Client client = Client.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    public static ClientModel clientModel = ClientModel.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    public static ClientModel clientModelWithAddress = ClientModel.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .address(AddressFactory.addressModel)
            .build();
}
