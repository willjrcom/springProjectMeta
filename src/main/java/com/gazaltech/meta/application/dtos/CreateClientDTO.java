package com.gazaltech.meta.application.dtos;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateClientDTO {
    
    @JsonProperty("name")
    @NotNull(message = "Name is required")
    private String name;

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("cpf")
    @CPF
    @NotNull(message = "Cpf is required")
    private String cpf;
}
