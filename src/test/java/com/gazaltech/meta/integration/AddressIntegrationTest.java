package com.gazaltech.meta.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.infrastructure.models.AddressModel;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AddressIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private AddressRepository addressRepository;

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
                        .zipCode("18085-430")
                        .build();

        private AddressModel addressModel = AddressModel.builder()
                        .street("Rua Piedade")
                        .number(226)
                        .neighborhood("Jardim Leocadia")
                        .city("Sorocaba")
                        .uf("SP")
                        .zipCode("18085-430")
                        .build();

        @BeforeEach
        void up() {
                addressRepository.save(addressModel);
                addressRepository.save(addressModel);
        }

        @AfterEach
        void down() {
                addressRepository.deleteAll();
        }

        @Test
        void createAddress_AllFields_Success() throws Exception {
                String json = objectMapper.writeValueAsString(createAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders.post("/addresses")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isCreated())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void createAddress_WithoutName_Error() throws Exception {
                createAddressDTO.setStreet(null);
                String json = objectMapper.writeValueAsString(createAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders.post("/addresses")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateAddress_AllFields_Success() throws Exception {
                String json = objectMapper.writeValueAsString(updateAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/addresses/" + addressModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateAddress_InvalidID_Error() throws Exception {
                String json = objectMapper.writeValueAsString(updateAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/addresses/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateAddress_NotFoundID_Error() throws Exception {
                String json = objectMapper.writeValueAsString(updateAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/addresses/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getAddressByID_ValidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/addresses/" + addressModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isOk())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getAddressByID_InvalidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/addresses/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getAddressByID_NotFoundID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/addresses/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteAddressByID_ValidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/addresses/" + addressModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteAddressByID_InvalidID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/addresses/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteAddressByID_NotFoundID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/addresses/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }
}
