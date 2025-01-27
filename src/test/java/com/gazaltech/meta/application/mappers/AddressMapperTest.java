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
        var domain = addressMapper.createDtoToDomain(AddressFactory.createAddressDTO);

        assertThat(domain.getId()).isNull();
        assertEquals(domain.getStreet(), AddressFactory.createAddressDTO.getStreet());
        assertEquals(domain.getNumber(), AddressFactory.createAddressDTO.getNumber());
        assertEquals(domain.getNeighborhood(), AddressFactory.createAddressDTO.getNeighborhood());
        assertEquals(domain.getCity(), AddressFactory.createAddressDTO.getCity());
        assertEquals(domain.getUf().toString(), AddressFactory.createAddressDTO.getUf());
        assertEquals(domain.getZipCode(), AddressFactory.createAddressDTO.getZipCode());
    }

    @Test
    @DisplayName("Domain to DTO test")
    void testDomainToDto() {
        var dto = addressMapper.domainToDto(AddressFactory.address);

        assertEquals(dto.getId(), AddressFactory.address.getId());
        assertEquals(dto.getStreet(), AddressFactory.address.getStreet());
        assertEquals(dto.getNumber(), AddressFactory.address.getNumber());
        assertEquals(dto.getNeighborhood(), AddressFactory.address.getNeighborhood());
        assertEquals(dto.getCity(), AddressFactory.address.getCity());
        assertEquals(dto.getUf(), AddressFactory.address.getUf().toString());
        assertEquals(dto.getZipCode(), AddressFactory.address.getZipCode());
    }

    @Test
    @DisplayName("Domain to Model test")
    void testDomainToModel() {
        var model = addressMapper.domainToModel(AddressFactory.address);

        assertEquals(model.getId(), AddressFactory.address.getId());
        assertEquals(model.getStreet(), AddressFactory.address.getStreet());
        assertEquals(model.getNumber(), AddressFactory.address.getNumber());
        assertEquals(model.getNeighborhood(), AddressFactory.address.getNeighborhood());
        assertEquals(model.getCity(), AddressFactory.address.getCity());
        assertEquals(model.getUf(), AddressFactory.address.getUf().toString());
        assertEquals(model.getZipCode(), AddressFactory.address.getZipCode());
    }

    @Test
    @DisplayName("Model to Domain test")
    void testModelToDomain() {
        var domain = addressMapper.modelToDomain(AddressFactory.addressModel);

        assertEquals(domain.getId(), AddressFactory.addressModel.getId());
        assertEquals(domain.getStreet(), AddressFactory.addressModel.getStreet());
        assertEquals(domain.getNumber(), AddressFactory.addressModel.getNumber());
        assertEquals(domain.getNeighborhood(), AddressFactory.addressModel.getNeighborhood());
        assertEquals(domain.getCity(), AddressFactory.addressModel.getCity());
        assertEquals(domain.getUf().toString(), AddressFactory.addressModel.getUf());
        assertEquals(domain.getZipCode(), AddressFactory.addressModel.getZipCode());
    }

    @Test
    @DisplayName("Update Domain from DTO test")
    void testUpdateDomainFromDto() {
        addressMapper.updateDomainFromDto(AddressFactory.updateAddressDTO, AddressFactory.address);

        assertThat(AddressFactory.address.getId()).isNotNull();
        assertEquals(AddressFactory.address.getStreet(), AddressFactory.updateAddressDTO.getStreet());
        assertEquals(AddressFactory.address.getStreet(), AddressFactory.updateAddressDTO.getStreet());
        assertEquals(AddressFactory.address.getNumber(), AddressFactory.updateAddressDTO.getNumber());
        assertEquals(AddressFactory.address.getNeighborhood(), AddressFactory.updateAddressDTO.getNeighborhood());
        assertEquals(AddressFactory.address.getCity(), AddressFactory.updateAddressDTO.getCity());
        assertEquals(AddressFactory.address.getUf().toString(), AddressFactory.updateAddressDTO.getUf());
        assertEquals(AddressFactory.address.getZipCode(), AddressFactory.updateAddressDTO.getZipCode());
    }

    @Test
    @DisplayName("Update Domain from Via Cep Response test")
    void updateDomainFromViaCepResponse() {
        addressMapper.updateDomainFromDto(AddressFactory.updateAddressDTO, AddressFactory.address);

        assertThat(AddressFactory.address.getId()).isNotNull();
        assertEquals(AddressFactory.address.getStreet(), AddressFactory.updateAddressDTO.getStreet());
        assertEquals(AddressFactory.address.getStreet(), AddressFactory.updateAddressDTO.getStreet());
        assertEquals(AddressFactory.address.getNumber(), AddressFactory.updateAddressDTO.getNumber());
        assertEquals(AddressFactory.address.getNeighborhood(), AddressFactory.updateAddressDTO.getNeighborhood());
        assertEquals(AddressFactory.address.getCity(), AddressFactory.updateAddressDTO.getCity());
        assertEquals(AddressFactory.address.getUf().toString(), AddressFactory.updateAddressDTO.getUf());
        assertEquals(AddressFactory.address.getZipCode(), AddressFactory.updateAddressDTO.getZipCode());
    }
}
