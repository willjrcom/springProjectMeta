package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gazaltech.meta.domain.Client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("address")
    private AddressDTO address;

    public static ClientDTO fromDomain(Client client) {
        if (client == null) {
            return null;
        }
        
        var clientDTO = new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                AddressDTO.fromDomain(client.getAddress()));

        return clientDTO;
    }
}
