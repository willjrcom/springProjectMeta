package com.gazaltech.meta.models;

import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String uf;
    private String country;
    private String zipCode;

    public Address toDomain() {
        return new Address(id, street, number, neighborhood, city, Uf.valueOf(uf), country, zipCode);
    }

    public static AddressModel toModel(Address address) {
        return new AddressModel(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getUf().toString(), address.getCountry(), address.getZipCode());
    }
}
