package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateAddressDTO {
    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    @Min(1)
    private Integer number;

    @JsonProperty("complement")
    private String complement;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("city")
    private String city;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("zip_code")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    private String zipCode;
}