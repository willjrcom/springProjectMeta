package com.gazaltech.meta.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.application.mappers.ClientMapper;
import com.gazaltech.meta.application.ports.client.ClientPort;
import com.gazaltech.meta.infrastructure.repositories.AddressRepositoryImpl;
import com.gazaltech.meta.infrastructure.repositories.ClientRepositoryImpl;
import com.gazaltech.meta.models.AddressModel;
import com.gazaltech.meta.models.ClientModel;
import com.gazaltech.meta.shared.exceptions.BadRequestException;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class ClientUseCase implements ClientPort {

    @Autowired
    private ClientRepositoryImpl clientRepository;

    @Autowired
    private AddressRepositoryImpl addressRepository;

    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    @Override
    public String createClient(CreateClientDTO clientDTO) {
        var client = clientMapper.createDtoToDomain(clientDTO);
        var clientModel = ClientModel.toModel(client);

        var result = clientRepository.save(clientModel);
        return result.toDomain().getId().toString();
    }

    @Override
    public void updateClient(Long id, UpdateClientDTO clientDTO) {
        clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .map(client -> {
                    clientMapper.updateDomainFromDto(clientDTO, client);
                    return client;
                })
                .map(ClientModel::toModel)
                .map(clientModel -> {
                    return clientRepository.save(clientModel);
                })
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @Override
    public void deleteClientByID(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("Client not found with id: " + id);
        }
        
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO getClientByID(Long id) {
        return clientRepository.findById(id)
                .map(clientModel -> clientModel.toDomain())
                .map(client -> clientMapper.domainToDto(client))
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @Override
    public List<ClientDTO> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var clientModels = clientRepository.findAll(pageable);

        return clientModels.stream()
                .map(ClientModel::toDomain)
                .map(client -> clientMapper.domainToDto(client))
                .collect(Collectors.toList());
    }

    @Override
    public void addAddress(Long id, Long addressID) {
        var client = clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        if (client.getAddress() != null) {
            throw new BadRequestException("Client already has an address");
        }
        
        var address = addressRepository.findById(addressID)
                .map(AddressModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Address not found"));
        
        client.setAddress(address);
        
        var clientModel = ClientModel.toModel(client);
        clientRepository.save(clientModel);
    }

    @Override
    public void removeAddress(Long id) {
        var client = clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        client.setAddress(null);

        var clientModel = ClientModel.toModel(client);
        clientRepository.save(clientModel);
    }
}
