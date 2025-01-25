package com.gazaltech.meta.application.ports.address;

import com.gazaltech.meta.application.dtos.CreateAddressDTO;

public interface CreateAddress {
    String createAddress(CreateAddressDTO address);
}
