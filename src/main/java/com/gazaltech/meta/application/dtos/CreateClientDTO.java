package com.gazaltech.meta.application.dtos;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateClientDTO {
    
    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("email")
    @Email
    private String email;

    @JsonProperty("cpf")
    @CPF
    @NotNull
    private String cpf;
}
