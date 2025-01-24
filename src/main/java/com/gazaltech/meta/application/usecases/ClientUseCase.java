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
import com.gazaltech.meta.application.ports.ClientPort;
import com.gazaltech.meta.infrastructure.repositories.AddressRepositoryImpl;
import com.gazaltech.meta.infrastructure.repositories.ClientRepositoryImpl;
import com.gazaltech.meta.models.AddressModel;
import com.gazaltech.meta.models.ClientModel;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class ClientUseCase implements ClientPort {

    @Autowired
    private ClientRepositoryImpl clientRepository;

    @Autowired
    private AddressRepositoryImpl addressRepository;

    @Override
    public String createClient(CreateClientDTO clientDTO) {
        var client = clientDTO.toDomain();
        var clientModel = ClientModel.toModel(client);

        var result = clientRepository.save(clientModel);
        return result.toDomain().getId().toString();
    }

    @Override
    public void updateClient(Long id, UpdateClientDTO clientDTO) {
        clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .map(client -> {
                    clientDTO.updateDomain(client);
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
        clientRepository.findById(id)
                .ifPresentOrElse(
                        client -> clientRepository.deleteById(id),
                        () -> {
                            throw new NotFoundException("Client not found");
                        });
    }

    @Override
    public ClientDTO getClientByID(Long id) {
        return clientRepository.findById(id)
                .map(clientModel -> clientModel.toDomain())
                .map(client -> ClientDTO.fromDomain(client))
                .orElseThrow(() -> new NotFoundException("Client not found"));
    }

    @Override
    public List<ClientDTO> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var clientModels = clientRepository.findAll(pageable);

        return clientModels.stream()
                .map(ClientModel::toDomain)
                .map(client -> ClientDTO.fromDomain(client))
                .collect(Collectors.toList());
    }

    @Override
    public void addAddress(Long id, Long addressID) {
        var client = clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        var address = addressRepository.findById(addressID)
                .map(AddressModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Address not found"));
        
        client.addAddress(address);
        
        clientRepository.save(ClientModel.toModel(client));
    }

    @Override
    public void removeAddress(Long id, Long addressID) {
        var client = clientRepository.findById(id)
                .map(ClientModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        var address = addressRepository.findById(addressID)
                .map(AddressModel::toDomain)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        client.removeAddress(address);

        clientRepository.save(ClientModel.toModel(client));
    }
}
