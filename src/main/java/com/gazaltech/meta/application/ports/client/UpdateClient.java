package com.gazaltech.meta.application.ports.client;

import com.gazaltech.meta.application.dtos.UpdateClientDTO;

public interface UpdateClient {
    void updateClient(Long clientID, UpdateClientDTO client);

    void addAddress(Long id, Long addressID);

    void removeAddress(Long id);
}
