package com.gazaltech.meta.application.ports.client;

import com.gazaltech.meta.application.dtos.CreateClientDTO;

public interface CreateClientPort {
    String createClient(CreateClientDTO client);
}
