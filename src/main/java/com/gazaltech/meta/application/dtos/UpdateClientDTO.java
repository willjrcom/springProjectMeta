package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gazaltech.meta.domain.Client;

public class UpdateClientDTO {
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    public void updateDomain(Client client) {
        if (name != null) {
            client.setName(name);
        }

        if (email != null) {
            client.setEmail(email);
        }
    }
}
