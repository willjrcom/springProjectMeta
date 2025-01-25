package com.gazaltech.meta.application.ports.address;

import java.util.List;

import com.gazaltech.meta.application.dtos.AddressDTO;

public interface GetAddress {

    AddressDTO getAddressByID(Long id);

    List<AddressDTO> getAllAddresses(int page, int size);
}
