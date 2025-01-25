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
import com.gazaltech.meta.infrastructure.repositories.AddressRepositoryImpl;
import com.gazaltech.meta.infrastructure.services.viaCep.ViaCepClient;
import com.gazaltech.meta.models.AddressModel;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class AddressUseCase implements AddressPort {

    @Autowired
    private AddressRepositoryImpl addressRepository;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public String createAddress(CreateAddressDTO addressDTO) {
        var address = addressMapper.createDtoToDomain(addressDTO);

        var cep = address.getZipCode().replace("-", "");
        var viaCepResponse = viaCepClient.getAddressByZipCode(cep);
        viaCepResponse.updateDomain(address);

        var addressModel = AddressModel.toModel(address);

        var result = addressRepository.save(addressModel);
        return result.toDomain().getId().toString();
    }

    @Override
    public void updateAddress(Long id, UpdateAddressDTO addressDTO) {
        addressRepository.findById(id)
                .map(AddressModel::toDomain)
                .map(address -> {
                    addressMapper.updateDomainFromDto(addressDTO, address);

                    var cep = address.getZipCode().replace("-", "");
                    var viaCepResponse = viaCepClient.getAddressByZipCode(cep);
                    viaCepResponse.updateDomain(address);

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
        if(!addressRepository.existsById(id)) {
            throw new NotFoundException("Address not found with id: " + id);
        }
        
        addressRepository.deleteById(id);
    }

    @Override
    public AddressDTO getAddressByID(Long id) {
        return addressRepository.findById(id)
                .map(addressModel -> addressModel.toDomain())
                .map(address -> addressMapper.domainToDto(address))
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public List<AddressDTO> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var addressModels = addressRepository.findAll(pageable);

        return addressModels.stream()
                .map(AddressModel::toDomain)
                .map(address -> addressMapper.domainToDto(address))
                .collect(Collectors.toList());
    }
}
