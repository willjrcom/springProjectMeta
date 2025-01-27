package com.gazaltech.meta.application.ports.address;

import java.util.List;

import com.gazaltech.meta.application.dtos.AddressDTO;

public interface GetAddressPort {

    AddressDTO getAddressByID(Long id);

    List<AddressDTO> getAddressesByZipCode(String zipCode);

    AddressDTO searchZipCode(String zipCode);

    List<AddressDTO> getAllAddresses(int page, int size);
}
