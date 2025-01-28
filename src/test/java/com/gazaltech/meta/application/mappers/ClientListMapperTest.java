package com.gazaltech.meta.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.factories.ClientFactory;
import com.gazaltech.meta.infrastructure.models.ClientModel;

@SpringBootTest()
public class ClientListMapperTest {

    private final List<ClientModel> clientModels = List.of(ClientFactory.clientModel(), ClientFactory.clientModel());
    private final List<Client> clients = List.of(ClientFactory.client(), ClientFactory.client());

    @Autowired
    private ClientListMapperImpl clientListMapper;

    @Test
    @DisplayName("Domains to Dtos")
    void testDomainsToDtos() {
        var dtos = clientListMapper.domainsToDtos(clients);

        assertEquals(clients.size(), dtos.size());
        
        for (int i = 0; i < clients.size(); i++) {
            assertEquals(clients.get(i).getId(), dtos.get(i).getId());
            assertEquals(clients.get(i).getName(), dtos.get(i).getName());
            assertEquals(clients.get(i).getEmail(), dtos.get(i).getEmail());
            assertEquals(clients.get(i).getCpf(), dtos.get(i).getCpf());
        }
    }

    @Test
    @DisplayName("Models to Domains")
    void testModelsToDomains() {
        var domains = clientListMapper.modelsToDomains(clientModels);

        assertEquals(clientModels.size(), domains.size());

        for (int i = 0; i < clientModels.size(); i++) {
            assertEquals(clientModels.get(i).getId(), domains.get(i).getId());
            assertEquals(clientModels.get(i).getName(), domains.get(i).getName());
            assertEquals(clientModels.get(i).getEmail(), domains.get(i).getEmail());
            assertEquals(clientModels.get(i).getCpf(), domains.get(i).getCpf());
        }
    }
}
