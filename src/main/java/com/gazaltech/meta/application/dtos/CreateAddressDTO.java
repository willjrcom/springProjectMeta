package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAddressDTO {

    @JsonProperty("street")
    @NotNull
    private String street;

    @JsonProperty("number")
    @Min(1)
    private String number;

    @JsonProperty("neighborhood")
    @NotNull
    private String neighborhood;

    @JsonProperty("city")
    @NotNull
    private String city;

    @JsonProperty("uf")
    @Enumerated(EnumType.STRING)
    @NotNull
    private String uf;

    @JsonProperty("zip_code")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    @NotNull
    private String zipCode;
}