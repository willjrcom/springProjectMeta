package com.gazaltech.meta.factories;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;
import com.gazaltech.meta.infrastructure.models.AddressModel;
import com.gazaltech.meta.infrastructure.services.getCep.ViaCepResponse;

public class AddressFactory {
        public static CreateAddressDTO createAddressDTO = CreateAddressDTO.builder()
                        .street("Rua Piedade")
                        .number(226)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf("SP")
                        .zipCode("18085-430")
                        .build();

        public static UpdateAddressDTO updateAddressDTO = UpdateAddressDTO.builder()
                        .street("Rua Piedade")
                        .number(228)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf("SP")
                        .zipCode("18085-440")
                        .build();

        public static AddressDTO addressDTO = AddressDTO.builder()
                        .id(1L)
                        .street("Rua Piedade")
                        .number(226)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf("SP")
                        .zipCode("18085-430")
                        .build();

        public static Address address = Address.builder()
                        .id(1L)
                        .street("Rua Piedade")
                        .number(226)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf(Uf.valueOf("SP"))
                        .zipCode("18085-430")
                        .build();

        public static AddressModel addressModel = AddressModel.builder()
                        .id(1L)
                        .street("Rua Piedade")
                        .number(226)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf("SP")
                        .zipCode("18085-430")
                        .build();

        public static ViaCepResponse viaCepResponse = ViaCepResponse.builder()
                        .logradouro("Rua Piedade")
                        .bairro("Jardim Leocadia")
                        .localidade("Sorocaba")
                        .uf("SP")
                        .cep("18085-430")
                        .build();
}
