package com.gazaltech.meta.application.ports.address;

import com.gazaltech.meta.application.dtos.UpdateAddressDTO;

public interface UpdateAddress {
    void updateAddress(Long id, UpdateAddressDTO address);
}
