package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gazaltech.meta.domain.Client;

public class CreateClientDTO {
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    public Client toDomain() {
        return new Client(null, name, email, null);
    }
}
