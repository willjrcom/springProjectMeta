package com.gazaltech.meta.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.infrastructure.models.AddressModel;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Save all fields")
    public void testSave_AllFields_Success() {
        AddressModel result = addressRepository.save(AddressFactory.addressModel);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStreet()).isEqualTo(AddressFactory.addressModel.getStreet());
        assertThat(result.getNumber()).isEqualTo(AddressFactory.addressModel.getNumber());
        assertThat(result.getNeighborhood()).isEqualTo(AddressFactory.addressModel.getNeighborhood());
        assertThat(result.getCity()).isEqualTo(AddressFactory.addressModel.getCity());
        assertThat(result.getUf()).isEqualTo(AddressFactory.addressModel.getUf());
        assertThat(result.getZipCode()).isEqualTo(AddressFactory.addressModel.getZipCode());
    }

    @Test
    @DisplayName("Find by ID")
    public void testFindById_ValidID_Success() {
        addressRepository.save(AddressFactory.addressModel);
        AddressModel result = addressRepository.findById(AddressFactory.addressModel.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(AddressFactory.addressModel.getId());
        assertThat(result.getStreet()).isEqualTo(AddressFactory.addressModel.getStreet());
    }

    @Test
    @DisplayName("Find by ID - Invalid ID")
    public void testFindById_InvalidID_Error() {
        AddressModel result = addressRepository.findById(999L).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Delete by ID")
    public void testDelete_ValidID_Success() {
        addressRepository.save(AddressFactory.addressModel);
        addressRepository.deleteById(AddressFactory.addressModel.getId());
        AddressModel result = addressRepository.findById(AddressFactory.addressModel.getId()).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Delete by ID - Invalid ID")
    public void testDelete_InvalidID_Error() {
        addressRepository.deleteById(999L);
        AddressModel result = addressRepository.findById(999L).orElse(null);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Update")
    public void testUpdate_NewNumber_Success() {
        AddressFactory.addressModel.setNumber(228);
        addressRepository.save(AddressFactory.addressModel);

        AddressModel result = addressRepository.findById(AddressFactory.addressModel.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(AddressFactory.addressModel.getId());
        assertThat(result.getStreet()).isEqualTo(AddressFactory.addressModel.getStreet());
        assertThat(result.getNumber()).isEqualTo(AddressFactory.addressModel.getNumber());
    }
}
