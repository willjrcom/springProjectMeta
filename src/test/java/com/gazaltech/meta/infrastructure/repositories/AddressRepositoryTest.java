package com.gazaltech.meta.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void up() {
        addressRepository.deleteAll();
    }
    @Test
    @DisplayName("Save all fields")
    public void testSave_AllFields_Success() {
        var addressModel = AddressFactory.addressModelWithoutID();
        addressRepository.save(addressModel);

        assertThat(addressModel).isNotNull();
        assertThat(addressModel.getId()).isNotNull();
    }

    @Test
    @DisplayName("Find by ID")
    public void testFindById_ValidID_Success() {
        var addressModel = AddressFactory.addressModelWithoutID();
        addressRepository.save(addressModel);

        AddressModel result = addressRepository.findById(addressModel.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(addressModel.getId());
        assertThat(result.getStreet()).isEqualTo(addressModel.getStreet());
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
        var addressModel = AddressFactory.addressModelWithoutID();
        addressRepository.save(addressModel);
        addressRepository.deleteById(addressModel.getId());
        AddressModel result = addressRepository.findById(addressModel.getId()).orElse(null);
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
        var addressModel = AddressFactory.addressModelWithoutID();
        addressModel.setNumber(228);
        addressRepository.save(addressModel);

        AddressModel result = addressRepository.findById(addressModel.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(addressModel.getId());
        assertThat(result.getStreet()).isEqualTo(addressModel.getStreet());
        assertThat(result.getNumber()).isEqualTo(addressModel.getNumber());
    }
}
