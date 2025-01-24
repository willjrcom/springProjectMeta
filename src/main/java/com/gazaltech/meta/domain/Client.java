package com.gazaltech.meta.domain;

import java.util.List;

import com.gazaltech.meta.shared.exceptions.BadRequestException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private List<Address> addresses;

    public void addAddress(Address address) {
        if (addresses.size() >= 3) {
            throw new BadRequestException("O cliente já possui 3 endereços cadastrados.");
        }

        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }
}
