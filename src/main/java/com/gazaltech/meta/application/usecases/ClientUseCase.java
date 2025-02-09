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
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.application.mappers.ClientListMapper;
import com.gazaltech.meta.application.mappers.ClientMapper;
import com.gazaltech.meta.application.ports.client.ClientPort;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.repositories.ClientRepository;
import com.gazaltech.meta.shared.exceptions.BadRequestException;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class ClientUseCase implements ClientPort {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientListMapper clientListMapper;

    @Autowired
    private AddressMapper addressMapper;
    
    @Override
    public String createClient(CreateClientDTO clientDTO) {
        var client = clientMapper.createDtoToDomain(clientDTO);
        var clientModel = clientMapper.domainToModel(client);

        clientRepository.save(clientModel);
        var clientCreated = clientMapper.modelToDomain(clientModel);
        return clientCreated.getId().toString();
    }

    @Override
    public void updateClient(Long id, UpdateClientDTO clientDTO) {
        var optionalClientModel = clientRepository.findById(id);

        if (!optionalClientModel.isPresent()) {
            throw new NotFoundException("Client not found");
        }

        var client = clientMapper.modelToDomain(optionalClientModel.get());
        clientMapper.updateDomainFromDto(clientDTO, client);
        var clientModel = clientMapper.domainToModel(client);
        clientRepository.save(clientModel);
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
                .map(clientModel -> clientMapper.modelToDomain(clientModel))
                .map(client -> clientMapper.domainToDto(client))
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @Override
    public List<ClientDTO> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var clientModels = clientRepository.findAll(pageable).stream().collect(Collectors.toList());
        var clients = clientListMapper.modelsToDomains(clientModels);
        return clientListMapper.domainsToDtos(clients);
    }

    @Override
    public void addAddress(Long id, Long addressID) {
        var client = clientRepository.findById(id)
                .map(clientModel -> clientMapper.modelToDomain(clientModel))
                .orElseThrow(() -> new NotFoundException("Client not found"));

        if (client.getAddress() != null) {
            throw new BadRequestException("Client already has an address");
        }
        
        var address = addressRepository.findById(addressID)
                .map(addressModel -> addressMapper.modelToDomain(addressModel))
                .orElseThrow(() -> new NotFoundException("Address not found"));
        
        client.setAddress(address);
        
        var clientModel = clientMapper.domainToModel(client);
        clientRepository.save(clientModel);
    }

    @Override
    public void removeAddress(Long id) {
        var client = clientRepository.findById(id)
                .map(clientModel -> clientMapper.modelToDomain(clientModel))
                .orElseThrow(() -> new NotFoundException("Client not found"));

        client.setAddress(null);

        var clientModel = clientMapper.domainToModel(client);
        clientRepository.save(clientModel);
    }
}
