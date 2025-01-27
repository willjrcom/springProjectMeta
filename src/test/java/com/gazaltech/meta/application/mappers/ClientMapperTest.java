package com.gazaltech.meta.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.infrastructure.models.ClientModel;

@SpringBootTest
public class ClientMapperTest {
    private final CreateClientDTO createClientDTO = CreateClientDTO.builder()
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final UpdateClientDTO updateClientDTO = UpdateClientDTO.builder()
            .name("William Junior")
            .email("williamjunior@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final Client client = Client.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final ClientModel clientModel = ClientModel.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    @Autowired
    private ClientMapperImpl clientMapper;

    @Test
    @DisplayName("Create to Domain test")
    void testCreateDtoToDomain() {
        var domain = clientMapper.createDtoToDomain(createClientDTO);

        assertThat(domain.getId()).isNull();
        assertEquals(domain.getName(), createClientDTO.getName());
        assertEquals(domain.getEmail(), createClientDTO.getEmail());
        assertEquals(domain.getCpf(), createClientDTO.getCpf());
    }

    @Test
    @DisplayName("Domain to DTO test")
    void testDomainToDto() {
        var dto = clientMapper.domainToDto(client);

        assertEquals(dto.getId(), client.getId());
        assertEquals(dto.getName(), client.getName());
        assertEquals(dto.getCpf(), client.getCpf());
        assertEquals(dto.getEmail(), client.getEmail());
    }

    @Test
    @DisplayName("Domain to Model test")
    void testDomainToModel() {
        var model = clientMapper.domainToModel(client);

        assertEquals(model.getId(), client.getId());
        assertEquals(model.getName(), client.getName());
        assertEquals(model.getEmail(), client.getEmail());
        assertEquals(model.getCpf(), client.getCpf());
    }

    @Test
    @DisplayName("Model to Domain test")
    void testModelToDomain() {
        var domain = clientMapper.modelToDomain(clientModel);

        assertEquals(domain.getId(), clientModel.getId());
        assertEquals(domain.getName(), clientModel.getName());
        assertEquals(domain.getEmail(), clientModel.getEmail());
        assertEquals(domain.getCpf(), clientModel.getCpf());
    }

    @Test
    @DisplayName("Update Domain from DTO test")
    void testUpdateDomainFromDto() {
        clientMapper.updateDomainFromDto(updateClientDTO, client);

        assertThat(client.getId()).isNotNull();
        assertEquals(client.getName(), updateClientDTO.getName());
        assertEquals(client.getEmail(), updateClientDTO.getEmail());
        assertEquals(client.getCpf(), updateClientDTO.getCpf());
    }
}
