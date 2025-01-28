package com.gazaltech.meta.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gazaltech.meta.factories.ClientFactory;
import com.gazaltech.meta.infrastructure.models.ClientModel;

@DataJpaTest
public class ClientRepositoryTest {
    
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Save all fields")
    public void testSave_AllFields_Success() {
        var clientModel = ClientFactory.clientModelWithoutID();
        ClientModel result = clientRepository.save(clientModel);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(clientModel.getName());
        assertThat(result.getEmail()).isEqualTo(clientModel.getEmail());
        assertThat(result.getCpf()).isEqualTo(clientModel.getCpf());
    }

    @Test
    @DisplayName("Find by ID")
    public void testFindById_ValidID_Success() {
        var clientModel = ClientFactory.clientModelWithoutID();

        clientRepository.save(clientModel);
        ClientModel result = clientRepository.findById(clientModel.getId()).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(clientModel.getId());
        assertThat(result.getName()).isEqualTo(clientModel.getName());
        assertThat(result.getEmail()).isEqualTo(clientModel.getEmail());
        assertThat(result.getCpf()).isEqualTo(clientModel.getCpf());
    }

    @Test
    @DisplayName("Find by ID - Invalid ID")
    public void testFindById_InvalidID_Error() {
        ClientModel result = clientRepository.findById(999L).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Delete by ID")
    public void testDelete_ValidID_Success() {
        var clientModel = ClientFactory.clientModelWithoutID();
        clientRepository.save(clientModel);
        clientRepository.deleteById(clientModel.getId());
        ClientModel result = clientRepository.findById(clientModel.getId()).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Delete by ID - Invalid ID")
    public void testDelete_InvalidID_Error() {
        clientRepository.deleteById(999L);
        ClientModel result = clientRepository.findById(999L).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Update")
    public void testUpdate_NewName_Success() {
        var clientModel = ClientFactory.clientModelWithoutID();
        clientModel.setName("William Junior");
        clientRepository.save(clientModel);

        ClientModel result = clientRepository.findById(clientModel.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(clientModel.getId());
        assertThat(result.getName()).isEqualTo(clientModel.getName());
        assertThat(result.getEmail()).isEqualTo(clientModel.getEmail());
        assertThat(result.getCpf()).isEqualTo(clientModel.getCpf());
    }
}
