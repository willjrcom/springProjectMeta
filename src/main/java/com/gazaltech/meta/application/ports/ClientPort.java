package com.gazaltech.meta.application.ports;

import java.util.List;

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;

public interface ClientPort {
    String createClient(CreateClientDTO client);

    void updateClient(Long clientID, UpdateClientDTO client);

    void addAddress(Long id, Long addressID);

    void removeAddress(Long id, Long addressID);

    void deleteClientByID(Long id);

    ClientDTO getClientByID(Long id);

    List<ClientDTO> getAllClients(int page, int size);
}
