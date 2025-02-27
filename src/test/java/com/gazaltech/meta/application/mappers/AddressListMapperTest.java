package com.gazaltech.meta.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@SpringBootTest()
public class AddressListMapperTest {

    private final List<AddressModel> addressModels = List.of(AddressFactory.addressModel(), AddressFactory.addressModel());
    private final List<Address> addresses = List.of(AddressFactory.address(), AddressFactory.address());

    @Autowired
    private AddressListMapperImpl addressListMapper;

    @Test
    @DisplayName("Domains to Dtos")
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
    @DisplayName("Models to Domains")
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
