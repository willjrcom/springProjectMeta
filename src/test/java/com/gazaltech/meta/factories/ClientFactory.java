package com.gazaltech.meta.factories;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.infrastructure.models.ClientModel;

public class ClientFactory {

        public static CreateClientDTO createClientDTO () {
                return CreateClientDTO.builder()
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static UpdateClientDTO updateClientDTO () {
                return UpdateClientDTO.builder()
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static ClientDTO clientDTO () {
                return ClientDTO.builder()
                        .id(1L)
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static Client client() {
                return Client.builder()
                        .id(1L)
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static ClientModel clientModel () {
                return ClientModel.builder()
                        .id(1L)
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static ClientModel clientModelWithoutID () {
                return ClientModel.builder()
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .build();
        }

        public static ClientModel clientModelWithAddress () {
                return ClientModel.builder()
                        .id(1L)
                        .name("William")
                        .email("will@gmail.com")
                        .cpf("436.377.998-55")
                        .address(AddressFactory.addressModel())
                        .build();
        }
}
