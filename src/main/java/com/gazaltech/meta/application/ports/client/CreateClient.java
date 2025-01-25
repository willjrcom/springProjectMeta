package com.gazaltech.meta.application.ports.client;

import com.gazaltech.meta.application.dtos.CreateClientDTO;

public interface CreateClient {
    String createClient(CreateClientDTO client);
}
