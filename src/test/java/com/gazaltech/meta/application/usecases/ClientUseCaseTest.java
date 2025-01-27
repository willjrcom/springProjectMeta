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

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.application.mappers.ClientListMapper;
import com.gazaltech.meta.application.mappers.ClientMapper;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Client;
import com.gazaltech.meta.domain.Uf;
import com.gazaltech.meta.infrastructure.models.AddressModel;
import com.gazaltech.meta.infrastructure.models.ClientModel;
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

    private final CreateClientDTO createClientDTO = CreateClientDTO.builder()
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final UpdateClientDTO updateClientDTO = UpdateClientDTO.builder()
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final ClientDTO clientDTO = ClientDTO.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final Client client = Client.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final Address address = Address.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf(Uf.valueOf("SP"))
            .zipCode("18085-430")
            .build();

    private final ClientModel clientModel = ClientModel.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .build();

    private final AddressModel addressModel = AddressModel.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-430")
            .build();

    private final ClientModel clientModelWithAddress = ClientModel.builder()
            .id(1L)
            .name("William")
            .email("will@gmail.com")
            .cpf("436.377.998-55")
            .address(addressModel)
            .build();

    @Test
    @DisplayName("Add Address test")
    void testAddAddress_ValidAddressID_Success() {
        when(clientRepository.findById(clientModel.getId())).thenReturn(Optional.of(clientModel));
        when(clientRepository.save(clientModel)).thenReturn(clientModelWithAddress);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(addressModel));

        when(clientMapper.domainToModel(client)).thenReturn(clientModel);
        when(clientMapper.modelToDomain(clientModel)).thenReturn(client);

        when(addressMapper.modelToDomain(addressModel)).thenReturn(address);

        clientUseCase.addAddress(clientModel.getId(), addressModel.getId());

        assertThat(clientModelWithAddress.getAddress()).isNotNull();
    }

    @Test
    @DisplayName("Create Client test")
    void testCreateClient_AllFields_Success() {
        when(clientMapper.createDtoToDomain(createClientDTO)).thenReturn(client);
        when(clientMapper.domainToModel(client)).thenReturn(clientModel);
        when(clientMapper.modelToDomain(clientModel)).thenReturn(client);

        var id = clientUseCase.createClient(createClientDTO);

        assertEquals(client.getId().toString(), id);
        assertEquals(client.getName(), createClientDTO.getName());
        assertEquals(client.getEmail(), createClientDTO.getEmail());
        assertEquals(client.getCpf(), createClientDTO.getCpf());
    }

    @Test
    @DisplayName("Delete Client test")
    void testDeleteClientByID_ValidID_Success() {
        when(clientRepository.existsById(clientModel.getId())).thenReturn(true);

        clientUseCase.deleteClientByID(clientModel.getId());

        verify(clientRepository, times(1)).deleteById(clientModel.getId());
        verify(clientRepository, times(1)).existsById(clientModel.getId());
    }

    @Test
    @DisplayName("Get All Clients test")
    void testGetAllClients_Success() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

        when(clientRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(clientModel, clientModel)));

        when(clientListMapper.modelsToDomains(List.of(clientModel, clientModel))).thenReturn(List.of(client, client));
        when(clientListMapper.domainsToDtos(List.of(client, client))).thenReturn(List.of(clientDTO, clientDTO));

        var clients = clientUseCase.getAllClients(0, 10);

        assertEquals(clients.size(), 2);
    }

    @Test
    @DisplayName("Get Client by ID test")
    void testGetClientByID_ValidID_Success() {
        when(clientRepository.findById(clientModel.getId())).thenReturn(Optional.of(clientModel));

        when(clientMapper.domainToDto(client)).thenReturn(clientDTO);
        when(clientMapper.modelToDomain(clientModel)).thenReturn(client);

        var clientFound = clientUseCase.getClientByID(clientModel.getId());

        assertEquals(clientFound.getId(), clientModel.getId());
    }

    @Test
    @DisplayName("Remove Address test")
    void testRemoveAddress_ValidID_Success() {
        when(clientRepository.findById(clientModel.getId())).thenReturn(Optional.of(clientModel));
        when(clientRepository.save(clientModel)).thenReturn(clientModelWithAddress);

        when(clientMapper.domainToModel(client)).thenReturn(clientModel);
        when(clientMapper.modelToDomain(clientModel)).thenReturn(client);

        clientUseCase.removeAddress(clientModel.getId());

        verify(clientRepository, times(1)).save(clientModel);

        assertThat(clientModel.getAddress()).isNull();
    }

    @Test
    @DisplayName("Update Client test")
    void testUpdateClient_NewClient_Success() {
        when(clientRepository.findById(clientModel.getId())).thenReturn(Optional.of(clientModel));
        when(clientMapper.modelToDomain(clientModel)).thenReturn(client);
        when(clientMapper.domainToModel(client)).thenReturn(clientModel);

        clientUseCase.updateClient(clientModel.getId(), updateClientDTO);

        verify(clientRepository, times(1)).save(clientModel);

        assertEquals(clientModel.getId(), clientModel.getId());
        assertEquals(clientModel.getName(), updateClientDTO.getName());
        assertEquals(clientModel.getEmail(), updateClientDTO.getEmail());
        assertEquals(clientModel.getCpf(), updateClientDTO.getCpf());
    }
}
