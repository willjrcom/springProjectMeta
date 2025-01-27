package com.gazaltech.meta.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.application.mappers.ClientListMapper;
import com.gazaltech.meta.application.mappers.ClientMapper;
import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.factories.ClientFactory;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.repositories.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientUseCaseTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private ClientListMapper clientListMapper;

    @InjectMocks
    private ClientUseCase clientUseCase;

    @Test
    @DisplayName("Add Address test")
    void testAddAddress_ValidAddressID_Success() {
        when(clientRepository.findById(ClientFactory.clientModel.getId())).thenReturn(Optional.of(ClientFactory.clientModel));
        when(clientRepository.save(ClientFactory.clientModel)).thenReturn(ClientFactory.clientModelWithAddress);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(AddressFactory.addressModel));

        when(clientMapper.domainToModel(ClientFactory.client)).thenReturn(ClientFactory.clientModel);
        when(clientMapper.modelToDomain(ClientFactory.clientModel)).thenReturn(ClientFactory.client);

        when(addressMapper.modelToDomain(AddressFactory.addressModel)).thenReturn(AddressFactory.address);

        clientUseCase.addAddress(ClientFactory.clientModel.getId(), AddressFactory.addressModel.getId());

        assertThat(ClientFactory.clientModelWithAddress.getAddress()).isNotNull();
    }

    @Test
    @DisplayName("Create Client test")
    void testCreateClient_AllFields_Success() {
        when(clientMapper.createDtoToDomain(ClientFactory.createClientDTO)).thenReturn(ClientFactory.client);
        when(clientMapper.domainToModel(ClientFactory.client)).thenReturn(ClientFactory.clientModel);
        when(clientMapper.modelToDomain(ClientFactory.clientModel)).thenReturn(ClientFactory.client);

        var id = clientUseCase.createClient(ClientFactory.createClientDTO);

        assertEquals(ClientFactory.client.getId().toString(), id);
        assertEquals(ClientFactory.client.getName(), ClientFactory.createClientDTO.getName());
        assertEquals(ClientFactory.client.getEmail(), ClientFactory.createClientDTO.getEmail());
        assertEquals(ClientFactory.client.getCpf(), ClientFactory.createClientDTO.getCpf());
    }

    @Test
    @DisplayName("Delete Client test")
    void testDeleteClientByID_ValidID_Success() {
        when(clientRepository.existsById(ClientFactory.clientModel.getId())).thenReturn(true);

        clientUseCase.deleteClientByID(ClientFactory.clientModel.getId());

        verify(clientRepository, times(1)).deleteById(ClientFactory.clientModel.getId());
        verify(clientRepository, times(1)).existsById(ClientFactory.clientModel.getId());
    }

    @Test
    @DisplayName("Get All Clients test")
    void testGetAllClients_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

        when(clientRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(ClientFactory.clientModel, ClientFactory.clientModel)));

        when(clientListMapper.modelsToDomains(List.of(ClientFactory.clientModel, ClientFactory.clientModel))).thenReturn(List.of(ClientFactory.client, ClientFactory.client));
        when(clientListMapper.domainsToDtos(List.of(ClientFactory.client, ClientFactory.client))).thenReturn(List.of(ClientFactory.clientDTO, ClientFactory.clientDTO));

        var clients = clientUseCase.getAllClients(0, 10);

        assertEquals(clients.size(), 2);
    }

    @Test
    @DisplayName("Get Client by ID test")
    void testGetClientByID_ValidID_Success() {
        when(clientRepository.findById(ClientFactory.clientModel.getId())).thenReturn(Optional.of(ClientFactory.clientModel));

        when(clientMapper.domainToDto(ClientFactory.client)).thenReturn(ClientFactory.clientDTO);
        when(clientMapper.modelToDomain(ClientFactory.clientModel)).thenReturn(ClientFactory.client);

        var clientFound = clientUseCase.getClientByID(ClientFactory.clientModel.getId());

        assertEquals(clientFound.getId(), ClientFactory.clientModel.getId());
    }

    @Test
    @DisplayName("Remove Address test")
    void testRemoveAddress_ValidID_Success() {
        when(clientRepository.findById(ClientFactory.clientModel.getId())).thenReturn(Optional.of(ClientFactory.clientModel));
        when(clientRepository.save(ClientFactory.clientModel)).thenReturn(ClientFactory.clientModelWithAddress);

        when(clientMapper.domainToModel(ClientFactory.client)).thenReturn(ClientFactory.clientModel);
        when(clientMapper.modelToDomain(ClientFactory.clientModel)).thenReturn(ClientFactory.client);

        clientUseCase.removeAddress(ClientFactory.clientModel.getId());

        verify(clientRepository, times(1)).save(ClientFactory.clientModel);

        assertThat(ClientFactory.clientModel.getAddress()).isNull();
    }

    @Test
    @DisplayName("Update Client test")
    void testUpdateClient_NewClient_Success() {
        when(clientRepository.findById(ClientFactory.clientModel.getId())).thenReturn(Optional.of(ClientFactory.clientModel));
        when(clientMapper.modelToDomain(ClientFactory.clientModel)).thenReturn(ClientFactory.client);
        when(clientMapper.domainToModel(ClientFactory.client)).thenReturn(ClientFactory.clientModel);

        clientUseCase.updateClient(ClientFactory.clientModel.getId(), ClientFactory.updateClientDTO);

        verify(clientRepository, times(1)).save(ClientFactory.clientModel);

        assertEquals(ClientFactory.clientModel.getId(), ClientFactory.clientModel.getId());
        assertEquals(ClientFactory.clientModel.getName(), ClientFactory.updateClientDTO.getName());
        assertEquals(ClientFactory.clientModel.getEmail(), ClientFactory.updateClientDTO.getEmail());
        assertEquals(ClientFactory.clientModel.getCpf(), ClientFactory.updateClientDTO.getCpf());
    }
}
