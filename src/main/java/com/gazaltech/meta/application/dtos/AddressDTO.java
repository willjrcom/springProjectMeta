package com.gazaltech.meta.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gazaltech.meta.domain.Address;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    private String number;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("city")
    private String city;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("country")
    private String country;

    @JsonProperty("zip_code")
    private String zipCode;

    public static AddressDTO fromDomain(Address address) {
        if (address == null) {
            return null;
        }
        
        var addressDTO = new AddressDTO(
            address.getId(),
            address.getStreet(),
            address.getNumber(),
            address.getNeighborhood(),
            address.getCity(),
            address.getUf().toString(),
            address.getCountry(),
            address.getZipCode()
        );

        return addressDTO;
    }
}
