package com.gazaltech.meta.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.factories.ClientFactory;

@SpringBootTest
public class ClientMapperTest {

    @Autowired
    private ClientMapperImpl clientMapper;

    @Test
    @DisplayName("Create to Domain test")
    void testCreateDtoToDomain() {
        var domain = clientMapper.createDtoToDomain(ClientFactory.createClientDTO);

        assertThat(domain.getId()).isNull();
        assertEquals(domain.getName(), ClientFactory.createClientDTO.getName());
        assertEquals(domain.getEmail(), ClientFactory.createClientDTO.getEmail());
        assertEquals(domain.getCpf(), ClientFactory.createClientDTO.getCpf());
    }

    @Test
    @DisplayName("Domain to DTO test")
    void testDomainToDto() {
        var dto = clientMapper.domainToDto(ClientFactory.client);

        assertEquals(dto.getId(), ClientFactory.client.getId());
        assertEquals(dto.getName(), ClientFactory.client.getName());
        assertEquals(dto.getCpf(), ClientFactory.client.getCpf());
        assertEquals(dto.getEmail(), ClientFactory.client.getEmail());
    }

    @Test
    @DisplayName("Domain to Model test")
    void testDomainToModel() {
        var model = clientMapper.domainToModel(ClientFactory.client);

        assertEquals(model.getId(), ClientFactory.client.getId());
        assertEquals(model.getName(), ClientFactory.client.getName());
        assertEquals(model.getEmail(), ClientFactory.client.getEmail());
        assertEquals(model.getCpf(), ClientFactory.client.getCpf());
    }

    @Test
    @DisplayName("Model to Domain test")
    void testModelToDomain() {
        var domain = clientMapper.modelToDomain(ClientFactory.clientModel);

        assertEquals(domain.getId(), ClientFactory.clientModel.getId());
        assertEquals(domain.getName(), ClientFactory.clientModel.getName());
        assertEquals(domain.getEmail(), ClientFactory.clientModel.getEmail());
        assertEquals(domain.getCpf(), ClientFactory.clientModel.getCpf());
    }

    @Test
    @DisplayName("Update Domain from DTO test")
    void testUpdateDomainFromDto() {
        clientMapper.updateDomainFromDto(ClientFactory.updateClientDTO, ClientFactory.client);

        assertThat(ClientFactory.client.getId()).isNotNull();
        assertEquals(ClientFactory.client.getName(), ClientFactory.updateClientDTO.getName());
        assertEquals(ClientFactory.client.getEmail(), ClientFactory.updateClientDTO.getEmail());
        assertEquals(ClientFactory.client.getCpf(), ClientFactory.updateClientDTO.getCpf());
    }
}
