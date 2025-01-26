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
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.application.ports.address.AddressPort;
import com.gazaltech.meta.infrastructure.mappers.AddressModelMapper;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.services.viaCep.ViaCepClient;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class AddressUseCase implements AddressPort {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressModelMapper addressModelMapper;

    @Override
    public String createAddress(CreateAddressDTO addressDTO) {
        var address = addressMapper.createDtoToDomain(addressDTO);

        var cep = address.getZipCode().replace("-", "");
        var viaCepResponse = viaCepClient.getAddressByZipCode(cep);
        viaCepResponse.updateDomain(address);

        var addressModel = addressModelMapper.domainToModel(address);

        addressRepository.save(addressModel);
        var addressCreated = addressModelMapper.modelToDomain(addressModel);
        return addressCreated.getId().toString();
    }

    @Override
    public void updateAddress(Long id, UpdateAddressDTO addressDTO) {
        addressRepository.findById(id)
                .map(addressModel -> addressModelMapper.modelToDomain(addressModel))
                .map(address -> {
                    addressMapper.updateDomainFromDto(addressDTO, address);

                    var cep = address.getZipCode().replace("-", "");
                    var viaCepResponse = viaCepClient.getAddressByZipCode(cep);

                    viaCepResponse.updateDomain(address);
                    return address;
                }).map(address -> addressModelMapper.domainToModel(address))
                .map(addressModel -> {
                    return addressRepository.save(addressModel);
                })
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public void deleteAddressByID(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("Address not found with id: " + id);
        }

        addressRepository.deleteById(id);
    }

    @Override
    public AddressDTO getAddressByID(Long id) {
        return addressRepository.findById(id)
                .map(addressModel -> addressModelMapper.modelToDomain(addressModel))
                .map(address -> addressMapper.domainToDto(address))
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public List<AddressDTO> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var addressModels = addressRepository.findAll(pageable);

        return addressModels.stream()
                .map(addressModel -> addressModelMapper.modelToDomain(addressModel))
                .map(address -> addressMapper.domainToDto(address))
                .collect(Collectors.toList());
    }
}
