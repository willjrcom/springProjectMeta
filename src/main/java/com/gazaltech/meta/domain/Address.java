package com.gazaltech.meta.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String reference;
    private String neighborhood;
    private String city;
    private Uf uf;
    private String zipCode;
}
