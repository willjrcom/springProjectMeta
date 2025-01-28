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
        var createClientDTO = ClientFactory.createClientDTO();
        var domain = clientMapper.createDtoToDomain(createClientDTO);

        assertThat(domain.getId()).isNull();
        assertEquals(domain.getName(), createClientDTO.getName());
        assertEquals(domain.getEmail(), createClientDTO.getEmail());
        assertEquals(domain.getCpf(), createClientDTO.getCpf());
    }

    @Test
    @DisplayName("Domain to DTO test")
    void testDomainToDto() {
        var client = ClientFactory.client();
        var dto = clientMapper.domainToDto(client);

        assertEquals(dto.getId(), client.getId());
        assertEquals(dto.getName(), client.getName());
        assertEquals(dto.getCpf(), client.getCpf());
        assertEquals(dto.getEmail(), client.getEmail());
    }

    @Test
    @DisplayName("Domain to Model test")
    void testDomainToModel() {
        var client = ClientFactory.client();
        var model = clientMapper.domainToModel(client);

        assertEquals(model.getId(), client.getId());
        assertEquals(model.getName(), client.getName());
        assertEquals(model.getEmail(), client.getEmail());
        assertEquals(model.getCpf(), client.getCpf());
    }

    @Test
    @DisplayName("Model to Domain test")
    void testModelToDomain() {
        var clientModel = ClientFactory.clientModel();
        var domain = clientMapper.modelToDomain(clientModel);

        assertEquals(domain.getId(), clientModel.getId());
        assertEquals(domain.getName(), clientModel.getName());
        assertEquals(domain.getEmail(), clientModel.getEmail());
        assertEquals(domain.getCpf(), clientModel.getCpf());
    }

    @Test
    @DisplayName("Update Domain from DTO test")
    void testUpdateDomainFromDto() {
        var updateClientDTO = ClientFactory.updateClientDTO();
        var client = ClientFactory.client();
        
        clientMapper.updateDomainFromDto(updateClientDTO, client);

        assertThat(client.getId()).isNotNull();
        assertEquals(client.getName(), updateClientDTO.getName());
        assertEquals(client.getEmail(), updateClientDTO.getEmail());
        assertEquals(client.getCpf(), updateClientDTO.getCpf());
    }
}
