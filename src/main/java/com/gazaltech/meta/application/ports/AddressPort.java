package com.gazaltech.meta.application.ports;

import java.util.List;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;

public interface AddressPort {
    String createAddress(CreateAddressDTO address);

    void updateAddress(Long id, UpdateAddressDTO address);
    
    void deleteAddressByID(Long id);

    AddressDTO getAddressByID(Long id);

    List<AddressDTO> getAllAddresses(int page, int size);
}
