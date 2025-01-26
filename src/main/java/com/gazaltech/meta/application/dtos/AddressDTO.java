package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
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
    private String zipCode;
}
