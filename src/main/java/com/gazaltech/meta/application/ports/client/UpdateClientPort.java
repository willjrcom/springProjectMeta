package com.gazaltech.meta.application.ports.client;

import com.gazaltech.meta.application.dtos.UpdateClientDTO;

public interface UpdateClientPort {
    void updateClient(Long clientID, UpdateClientDTO client);

    void addAddress(Long id, Long addressID);

    void removeAddress(Long id);
}
