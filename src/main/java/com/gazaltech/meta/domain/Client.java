package com.gazaltech.meta.domain;

import java.util.ArrayList;
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
    private List<Address> addresses = new ArrayList<Address>();

    public void addAddress(Address address) {
        if (addresses.size() >= 3) {
            throw new BadRequestException("client already has 3 addresses");
        }

        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }
}
