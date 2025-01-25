package com.gazaltech.meta.application.ports.client;

import java.util.List;

import com.gazaltech.meta.application.dtos.ClientDTO;

public interface GetClient {
    ClientDTO getClientByID(Long id);

    List<ClientDTO> getAllClients(int page, int size);
}
