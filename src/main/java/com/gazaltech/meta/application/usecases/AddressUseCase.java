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
import com.gazaltech.meta.application.mappers.AddressListMapper;
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.application.ports.address.AddressPort;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.services.getCep.port.GetCepPort;
import com.gazaltech.meta.shared.exceptions.NotFoundException;

@Service
public class AddressUseCase implements AddressPort {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GetCepPort getCepService;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressListMapper addressListMapper;

    @Override
    public String createAddress(CreateAddressDTO addressDTO) {
        var address = addressMapper.createDtoToDomain(addressDTO);

        var cep = address.getZipCode().replace("-", "");
        var viaCepResponse = getCepService.getAddressByZipCode(cep);
        addressMapper.updateDomainFromViaCepResponse(viaCepResponse, address);

        var addressModel = addressMapper.domainToModel(address);

        addressRepository.save(addressModel);
        var addressCreated = addressMapper.modelToDomain(addressModel);
        return addressCreated.getId().toString();
    }

    @Override
    public void updateAddress(Long id, UpdateAddressDTO addressDTO) {
        var optionalAddressModel = addressRepository.findById(id);
        if (!optionalAddressModel.isPresent()) {
            throw new NotFoundException("Address not found");
        }

        var address = addressMapper.modelToDomain(optionalAddressModel.get());
        addressMapper.updateDomainFromDto(addressDTO, address);

        var cep = address.getZipCode().replace("-", "");
        var viaCepResponse = getCepService.getAddressByZipCode(cep);

        addressMapper.updateDomainFromViaCepResponse(viaCepResponse, address);
        var addressModel = addressMapper.domainToModel(address);

        addressRepository.save(addressModel);
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
                .map(addressModel -> addressMapper.modelToDomain(addressModel))
                .map(address -> addressMapper.domainToDto(address))
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public List<AddressDTO> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        var addressModels = addressRepository.findAll(pageable).stream().collect(Collectors.toList());

        var addresses = addressListMapper.modelsToDomains(addressModels);
        return addressListMapper.domainsToDtos(addresses);
    }

    @Override
    public List<AddressDTO> getAddressesByZipCode(String zipCode) {
        var addressModels = addressRepository.findByZipCode(zipCode);
        var addresses = addressListMapper.modelsToDomains(addressModels);
        return addressListMapper.domainsToDtos(addresses);
    }

    @Override
    public AddressDTO searchZipCode(String zipCode) {
        var cepWithoutDash = zipCode.replace("-", "");
        var viaCepResponse = getCepService.getAddressByZipCode(cepWithoutDash);

        var address = new Address();
        addressMapper.updateDomainFromViaCepResponse(viaCepResponse, address);
        var addressDTO = addressMapper.domainToDto(address);
        return addressDTO;
    }
}
