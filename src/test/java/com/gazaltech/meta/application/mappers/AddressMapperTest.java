package com.gazaltech.meta.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.factories.AddressFactory;

@SpringBootTest()
public class AddressMapperTest {

    @Autowired
    private AddressMapperImpl addressMapper;

    @Test
    @DisplayName("Create to Domain test")
    void testCreateDtoToDomain() {
        var createAddressDTO = AddressFactory.createAddressDTO();
        var domain = addressMapper.createDtoToDomain(createAddressDTO);

        assertThat(domain.getId()).isNull();
        assertEquals(domain.getStreet(), createAddressDTO.getStreet());
        assertEquals(domain.getNumber(), createAddressDTO.getNumber());
        assertEquals(domain.getNeighborhood(), createAddressDTO.getNeighborhood());
        assertEquals(domain.getCity(), createAddressDTO.getCity());
        assertEquals(domain.getUf().toString(), createAddressDTO.getUf());
        assertEquals(domain.getZipCode(), createAddressDTO.getZipCode());
    }

    @Test
    @DisplayName("Domain to DTO test")
    void testDomainToDto() {
        var address = AddressFactory.address();
        var dto = addressMapper.domainToDto(address);

        assertEquals(dto.getId(), address.getId());
        assertEquals(dto.getStreet(), address.getStreet());
        assertEquals(dto.getNumber(), address.getNumber());
        assertEquals(dto.getNeighborhood(), address.getNeighborhood());
        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getUf(), address.getUf().toString());
        assertEquals(dto.getZipCode(), address.getZipCode());
    }

    @Test
    @DisplayName("Domain to Model test")
    void testDomainToModel() {
        var address = AddressFactory.address();
        var model = addressMapper.domainToModel(address);

        assertEquals(model.getId(), address.getId());
        assertEquals(model.getStreet(), address.getStreet());
        assertEquals(model.getNumber(), address.getNumber());
        assertEquals(model.getNeighborhood(), address.getNeighborhood());
        assertEquals(model.getCity(), address.getCity());
        assertEquals(model.getUf(), address.getUf().toString());
        assertEquals(model.getZipCode(), address.getZipCode());
    }

    @Test
    @DisplayName("Model to Domain test")
    void testModelToDomain() {
        var addressModel = AddressFactory.addressModel();
        var domain = addressMapper.modelToDomain(addressModel);

        assertEquals(domain.getId(), addressModel.getId());
        assertEquals(domain.getStreet(), addressModel.getStreet());
        assertEquals(domain.getNumber(), addressModel.getNumber());
        assertEquals(domain.getNeighborhood(), addressModel.getNeighborhood());
        assertEquals(domain.getCity(), addressModel.getCity());
        assertEquals(domain.getUf().toString(), addressModel.getUf());
        assertEquals(domain.getZipCode(), addressModel.getZipCode());
    }

    @Test
    @DisplayName("Update Domain from DTO test")
    void testUpdateDomainFromDto() {
        var updateAddressDTO = AddressFactory.updateAddressDTO();
        var address = AddressFactory.address();
        
        addressMapper.updateDomainFromDto(updateAddressDTO, address);

        assertThat(address.getId()).isNotNull();
        assertEquals(address.getStreet(), updateAddressDTO.getStreet());
        assertEquals(address.getStreet(), updateAddressDTO.getStreet());
        assertEquals(address.getNumber(), updateAddressDTO.getNumber());
        assertEquals(address.getNeighborhood(), updateAddressDTO.getNeighborhood());
        assertEquals(address.getCity(), updateAddressDTO.getCity());
        assertEquals(address.getUf().toString(), updateAddressDTO.getUf());
        assertEquals(address.getZipCode(), updateAddressDTO.getZipCode());
    }

    @Test
    @DisplayName("Update Domain from Via Cep Response test")
    void updateDomainFromViaCepResponse() {
        var updateAddressDTO = AddressFactory.updateAddressDTO();
        var address = AddressFactory.address();
        addressMapper.updateDomainFromDto(updateAddressDTO, address);

        assertThat(address.getId()).isNotNull();
        assertEquals(address.getStreet(), updateAddressDTO.getStreet());
        assertEquals(address.getStreet(), updateAddressDTO.getStreet());
        assertEquals(address.getNumber(), updateAddressDTO.getNumber());
        assertEquals(address.getNeighborhood(), updateAddressDTO.getNeighborhood());
        assertEquals(address.getCity(), updateAddressDTO.getCity());
        assertEquals(address.getUf().toString(), updateAddressDTO.getUf());
        assertEquals(address.getZipCode(), updateAddressDTO.getZipCode());
    }
}
