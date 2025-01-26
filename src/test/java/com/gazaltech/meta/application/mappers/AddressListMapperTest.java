package com.gazaltech.meta.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@SpringBootTest()
public class AddressListMapperTest {
    private final AddressModel addressModel = AddressModel.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-430")
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

    private final List<AddressModel> addressModels = List.of(addressModel, addressModel);
    private final List<Address> addresses = List.of(address, address);

    @Autowired
    private AddressListMapperImpl addressListMapper;

    @Test
    void testDomainsToDtos() {
        var dtos = addressListMapper.domainsToDtos(addresses);

        assertEquals(addresses.size(), dtos.size());
        
        for (int i = 0; i < addresses.size(); i++) {
            assertEquals(addresses.get(i).getId(), dtos.get(i).getId());
            assertEquals(addresses.get(i).getStreet(), dtos.get(i).getStreet());
            assertEquals(addresses.get(i).getNumber(), dtos.get(i).getNumber());
            assertEquals(addresses.get(i).getNeighborhood(), dtos.get(i).getNeighborhood());
            assertEquals(addresses.get(i).getCity(), dtos.get(i).getCity());
            assertEquals(addresses.get(i).getUf().toString(), dtos.get(i).getUf());
            assertEquals(addresses.get(i).getZipCode(), dtos.get(i).getZipCode());
        }
    }

    @Test
    void testModelsToDomains() {
        var domains = addressListMapper.modelsToDomains(addressModels);

        assertEquals(addressModels.size(), domains.size());

        for (int i = 0; i < addressModels.size(); i++) {
            assertEquals(addressModels.get(i).getId(), domains.get(i).getId());
            assertEquals(addressModels.get(i).getStreet(), domains.get(i).getStreet());
            assertEquals(addressModels.get(i).getNumber(), domains.get(i).getNumber());
            assertEquals(addressModels.get(i).getNeighborhood(), domains.get(i).getNeighborhood());
            assertEquals(addressModels.get(i).getCity(), domains.get(i).getCity());
            assertEquals(addressModels.get(i).getUf(), domains.get(i).getUf().toString());
            assertEquals(addressModels.get(i).getZipCode(), domains.get(i).getZipCode());
        }
    }
}
