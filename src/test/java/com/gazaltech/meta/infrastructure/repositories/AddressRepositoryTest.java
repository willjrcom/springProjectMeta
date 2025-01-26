package com.gazaltech.meta.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gazaltech.meta.infrastructure.models.AddressModel;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private final AddressModel addressModel = AddressModel.builder()
    .street("Rua Piedade")
    .number("226")
    .neighborhood("Piedade")
    .city("Rio de Janeiro")
    .uf("RJ")
    .zipCode("18085-430")
    .build();

    @Test
    public void testSave() {
        AddressModel result = addressRepository.save(addressModel);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStreet()).isEqualTo(addressModel.getStreet());
        assertThat(result.getNumber()).isEqualTo(addressModel.getNumber());
        assertThat(result.getNeighborhood()).isEqualTo(addressModel.getNeighborhood());
        assertThat(result.getCity()).isEqualTo(addressModel.getCity());
        assertThat(result.getUf()).isEqualTo(addressModel.getUf());
        assertThat(result.getZipCode()).isEqualTo(addressModel.getZipCode());
    }
}
