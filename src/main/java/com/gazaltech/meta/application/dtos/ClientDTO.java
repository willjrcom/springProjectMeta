package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
