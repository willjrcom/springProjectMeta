package com.gazaltech.meta.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gazaltech.meta.application.mappers.AddressListMapper;
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.services.getCep.ViaCepClient;

@ExtendWith(MockitoExtension.class)
public class AddressUseCaseTest {
        @Mock
        private AddressRepository addressRepository;

        @Mock
        private ViaCepClient viaCepClient;

        @Mock
        private AddressMapper addressMapper;

        @Mock
        private AddressListMapper addressListMapper;

        @InjectMocks
        private AddressUseCase addressUseCase;

        @Test
        @DisplayName("Create Address test")
        void testCreateAddress_AllFields_Success() {
                var cep = AddressFactory.createAddressDTO.getZipCode().replace("-", "");
                when(viaCepClient.getAddressByZipCode(cep)).thenReturn(AddressFactory.viaCepResponse);

                when(addressMapper.createDtoToDomain(AddressFactory.createAddressDTO)).thenReturn(AddressFactory.address);
                when(addressMapper.domainToModel(AddressFactory.address)).thenReturn(AddressFactory.addressModel);
                when(addressMapper.modelToDomain(AddressFactory.addressModel)).thenReturn(AddressFactory.address);

                when(addressRepository.save(AddressFactory.addressModel)).thenReturn(AddressFactory.addressModel);

                var id = addressUseCase.createAddress(AddressFactory.createAddressDTO);
                assertEquals(id, AddressFactory.addressModel.getId().toString());
        }

        @Test
        @DisplayName("Delete Address test")
        void testDeleteAddressByID_ValidID_Success() {
                when(addressRepository.existsById(AddressFactory.addressModel.getId())).thenReturn(true);
                addressUseCase.deleteAddressByID(AddressFactory.addressModel.getId());

                verify(addressRepository, times(1)).deleteById(AddressFactory.addressModel.getId());
                verify(addressRepository, times(1)).existsById(AddressFactory.addressModel.getId());
        }

        @Test
        @DisplayName("Get Address by ID test")
        void testGetAddressByID_ValidID_Success() {
                when(addressMapper.domainToDto(AddressFactory.address)).thenReturn(AddressFactory.addressDTO);
                when(addressMapper.modelToDomain(AddressFactory.addressModel)).thenReturn(AddressFactory.address);

                when(addressRepository.findById(AddressFactory.addressModel.getId())).thenReturn(Optional.of(AddressFactory.addressModel));

                var addressFound = addressUseCase.getAddressByID(AddressFactory.addressModel.getId());

                assertEquals(addressFound.getId(), AddressFactory.addressModel.getId());
        }

        @Test
        @DisplayName("Get All Addresses test")
        void testGetAllAddresses_Success() {
                Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

                when(addressRepository.findAll(pageable))
                                .thenReturn(new PageImpl<>(List.of(AddressFactory.addressModel, AddressFactory.addressModel)));
                when(addressListMapper.modelsToDomains(List.of(AddressFactory.addressModel, AddressFactory.addressModel)))
                                .thenReturn(List.of(AddressFactory.address, AddressFactory.address));
                when(addressListMapper.domainsToDtos(List.of(AddressFactory.address, AddressFactory.address)))
                                .thenReturn(List.of(AddressFactory.addressDTO, AddressFactory.addressDTO));

                var addresses = addressUseCase.getAllAddresses(0, 10);

                assertEquals(addresses.size(), 2);
        }

        @Test
        @DisplayName("Update Address test")
        void testUpdateAddress_NewAddress_Success() {
                when(addressRepository.findById(AddressFactory.addressModel.getId())).thenReturn(Optional.of(AddressFactory.addressModel));
                var cep = AddressFactory.createAddressDTO.getZipCode().replace("-", "");
                when(viaCepClient.getAddressByZipCode(cep)).thenReturn(AddressFactory.viaCepResponse);

                when(addressMapper.domainToModel(AddressFactory.address)).thenReturn(AddressFactory.addressModel);
                when(addressMapper.modelToDomain(AddressFactory.addressModel)).thenReturn(AddressFactory.address);

                when(addressRepository.save(AddressFactory.addressModel)).thenReturn(AddressFactory.addressModel);

                addressUseCase.updateAddress(AddressFactory.addressModel.getId(), AddressFactory.updateAddressDTO);

                verify(addressRepository, times(1)).save(AddressFactory.addressModel);

                assertEquals(AddressFactory.addressModel.getId(), AddressFactory.addressModel.getId());
                assertNotEquals(AddressFactory.addressModel.getNumber(), AddressFactory.updateAddressDTO.getNumber());
        }
}
