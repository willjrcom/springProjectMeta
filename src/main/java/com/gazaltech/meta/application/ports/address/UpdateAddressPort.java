package com.gazaltech.meta.application.ports.address;

import com.gazaltech.meta.application.dtos.UpdateAddressDTO;

public interface UpdateAddressPort {
    void updateAddress(Long id, UpdateAddressDTO address);
}
