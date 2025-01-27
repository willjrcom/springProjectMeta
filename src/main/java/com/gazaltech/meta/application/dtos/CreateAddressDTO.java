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
    @NotNull(message = "Street is required")
    private String street;

    @JsonProperty("number")
    @Min(1)
    private Integer number;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("neighborhood")
    @NotNull(message = "Neighborhood is required")
    private String neighborhood;

    @JsonProperty("city")
    @NotNull(message = "City is required")
    private String city;

    @JsonProperty("uf")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "UF is required")
    private String uf;

    @JsonProperty("zip_code")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    private String zipCode;
}