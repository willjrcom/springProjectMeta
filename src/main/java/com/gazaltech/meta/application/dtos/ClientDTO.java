package com.gazaltech.meta.application.dtos;

import java.util.List;

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

    @JsonProperty("addresses")
    private List<AddressDTO> addresses;

    public static ClientDTO fromDomain(Client client) {
        List<AddressDTO> addressDTOs = client.getAddresses()
                .stream()
                .map(AddressDTO::fromDomain)
                .toList();

        var clientDTO = new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getCpf(),
                addressDTOs);

        return clientDTO;
    }
}
