package com.gazaltech.meta.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.application.mappers.AddressListMapper;
import com.gazaltech.meta.application.mappers.AddressMapper;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;
import com.gazaltech.meta.infrastructure.models.AddressModel;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.services.viaCep.ViaCepClient;
import com.gazaltech.meta.infrastructure.services.viaCep.ViaCepResponse;

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

    private final CreateAddressDTO createAddressDTO = CreateAddressDTO.builder()
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-430")
            .build();

    private final UpdateAddressDTO updateAddressDTO = UpdateAddressDTO.builder()
            .street("Rua Piedade")
            .number(228)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
            .zipCode("18085-440")
            .build();

            private final AddressDTO addressDTO = AddressDTO.builder()
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf("SP")
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

    private final Address address = Address.builder()
            .id(1L)
            .street("Rua Piedade")
            .number(226)
            .neighborhood("Jardim Leocadia")
            .city("Sorocaba")
            .uf(Uf.valueOf("SP"))
            .zipCode("18085-430")
            .build();

            private final ViaCepResponse viaCepResponse = ViaCepResponse.builder()
            .logradouro("Rua Piedade")
            .bairro("Jardim Leocadia")
            .localidade("Sorocaba")
            .uf("SP")
            .cep("18085-430")
            .build();

    @BeforeEach
    void setUp() {
        when(viaCepClient.getAddressByZipCode("18085430")).thenReturn(viaCepResponse);

        when(addressMapper.createDtoToDomain(createAddressDTO)).thenReturn(address);
        when(addressMapper.domainToModel(address)).thenReturn(addressModel);
        when(addressMapper.domainToDto(address)).thenReturn(addressDTO);
        when(addressMapper.modelToDomain(addressModel)).thenReturn(address);

        when(addressRepository.save(addressModel)).thenReturn(addressModel);
        when(addressRepository.findById(addressModel.getId())).thenReturn(Optional.of(addressModel));
    }

    @Test
    @DisplayName("")
    void testCreateAddress() {
        var id = addressUseCase.createAddress(createAddressDTO);
        assertEquals(id, addressModel.getId().toString());
    }

    @Test
    void testDeleteAddressByID() {
        var id = addressUseCase.createAddress(createAddressDTO);
        addressUseCase.deleteAddressByID(Long.parseLong(id));
    }

    @Test
    void testGetAddressByID() {
        var id = addressUseCase.createAddress(createAddressDTO);
        var addressFound = addressUseCase.getAddressByID(Long.parseLong(id));
        
        assertEquals(addressFound.getId(), id);
    }

    @Test
    void testGetAllAddresses() {
        addressUseCase.createAddress(createAddressDTO);
        addressUseCase.createAddress(createAddressDTO);
        var addresses = addressUseCase.getAllAddresses(0,10);

        assertEquals(addresses.size(), 2);
    }

    @Test
    void testUpdateAddress() {
        var id = addressUseCase.createAddress(createAddressDTO);
        addressUseCase.updateAddress(Long.parseLong(id), updateAddressDTO);

        assertEquals(createAddressDTO.getNumber(), updateAddressDTO.getNumber());
    }
}
