package com.gazaltech.meta.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.application.ports.AddressPort;
import com.gazaltech.meta.infrastructure.repositories.AddressRepositoryImpl;
import com.gazaltech.meta.models.AddressModel;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class AddressUseCase implements AddressPort {

    @Autowired
    private AddressRepositoryImpl addressRepository;

    @Override
    public String createAddress(CreateAddressDTO addressDTO) {
        var address = addressDTO.toDomain();
        var addressModel = AddressModel.toModel(address);

        var result = addressRepository.save(addressModel);
        return result.toDomain().getId().toString();
    }

    @Override
    public void updateAddress(Long id, UpdateAddressDTO addressDTO) {
        addressRepository.findById(id)
                .map(AddressModel::toDomain)
                .map(address -> {
                    addressDTO.updateDomain(address);
                    return address;
                })
                .map(AddressModel::toModel)
                .map(addressModel -> {
                    return addressRepository.save(addressModel);
                })
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public void deleteAddressByID(Long id) {
        addressRepository.findById(id)
                .ifPresentOrElse(
                        address -> addressRepository.deleteById(id),
                        () -> {
                            throw new NotFoundException("Address not found");
                        });
    }

    @Override
    public AddressDTO getAddressByID(Long id) {
        return addressRepository.findById(id)
                .map(addressModel -> addressModel.toDomain())
                .map(address -> AddressDTO.fromDomain(address))
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public List<AddressDTO> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var addressModels = addressRepository.findAll(pageable);

        return addressModels.stream()
                .map(AddressModel::toDomain)
                .map(address -> AddressDTO.fromDomain(address))
                .collect(Collectors.toList());
    }
}
