package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class UpdateAddressDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    @Min(1)
    private String number;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("city")
    private String city;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("zip_code")
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$")
    private String zipCode;

    public void updateDomain(Address address) {
        if (street != null) {
            address.setStreet(street);
        }

        if (number != null) {
            address.setNumber(number);
        }

        if (neighborhood != null) {
            address.setNeighborhood(neighborhood);
        }

        if (city != null) {
            address.setCity(city);
        }

        if (uf != null) {
            address.setUf(Uf.valueOf(uf));
        }

        if (zipCode != null) {
            address.setZipCode(zipCode);
        }
    }
}