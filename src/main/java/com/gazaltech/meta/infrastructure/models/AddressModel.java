package com.gazaltech.meta.infrastructure.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Street is required")
    private String street;

    @Min(value = 1, message = "Number must be greater than 0")
    private Integer number;

    private String complement;

    private String reference;

    @NotEmpty(message = "Neighborhood is required")
    private String neighborhood;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "UF is required")
    @Length(min = 2, max = 2, message = "UF must be 2 characters")
    private String uf;

    @NotEmpty(message = "Zip Code is required")
    @Length(min = 8, max = 9, message = "Zip Code must be 8 or 9 characters")
    private String zipCode;
}
