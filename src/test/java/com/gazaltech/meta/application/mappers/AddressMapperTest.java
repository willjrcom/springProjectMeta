package com.gazaltech.meta.application.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testCreateDtoToDomain() {
        var address = addressMapper.createDtoToDomain(createAddressDTO);

        assertThat(address.getId()).isNull();
        assertEquals(address.getStreet(), createAddressDTO.getStreet());
        assertEquals(address.getNumber(), createAddressDTO.getNumber());
        assertEquals(address.getNeighborhood(), createAddressDTO.getNeighborhood());
        assertEquals(address.getCity(), createAddressDTO.getCity());
        assertEquals(address.getUf().toString(), createAddressDTO.getUf());
        assertEquals(address.getZipCode(), createAddressDTO.getZipCode());
    }

    @Test
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
    void testDomainToModel() {
        var model = addressMapper.domainToModel(address);

        assertThat(model.getId()).isNull();
        assertEquals(model.getStreet(), address.getStreet());
        assertEquals(model.getNumber(), address.getNumber());
        assertEquals(model.getNeighborhood(), address.getNeighborhood());
        assertEquals(model.getCity(), address.getCity());
        assertEquals(model.getUf(), address.getUf().toString());
        assertEquals(model.getZipCode(), address.getZipCode());
    }

    @Test
    void testModelToDomain() {
        var address = addressMapper.modelToDomain(addressModel);

        assertThat(address.getId()).isNull();
        assertEquals(address.getStreet(), addressModel.getStreet());
        assertEquals(address.getNumber(), addressModel.getNumber());
        assertEquals(address.getNeighborhood(), addressModel.getNeighborhood());
        assertEquals(address.getCity(), addressModel.getCity());
        assertEquals(address.getUf().toString(), addressModel.getUf());
        assertEquals(address.getZipCode(), addressModel.getZipCode());
    }

    @Test
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
}
