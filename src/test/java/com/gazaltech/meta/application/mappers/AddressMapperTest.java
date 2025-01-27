package com.gazaltech.meta.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@SpringBootTest()
public class AddressMapperTest {
    private final CreateAddressDTO createAddressDTO = CreateAddressDTO.builder()
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-430")
            .build();

    private final UpdateAddressDTO updateAddressDTO = UpdateAddressDTO.builder()
            .street("Rua 7 de Setembro")
            .number(226)
            .neighborhood("Morumbi")
            .city("Sao Paulo")
            .uf("SP")
            .zipCode("19085-330")
            .build();

    private final Address address = Address.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf(Uf.valueOf("SP"))
            .zipCode("18085-430")
            .build();

    private final AddressModel addressModel = AddressModel.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-430")
            .build();

    @Autowired
    private AddressMapperImpl addressMapper;

    @Test
    @DisplayName("Create to Domain test")
    void testCreateDtoToDomain() {
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
