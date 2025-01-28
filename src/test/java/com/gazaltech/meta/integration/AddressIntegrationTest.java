package com.gazaltech.meta.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;

import org.junit.jupiter.api.AfterEach;
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

        @AfterEach
        void down() {
                addressRepository.deleteAll();
        }

        @Test
        void createAddress_AllFields_Success() throws Exception {
                String json = objectMapper.writeValueAsString(AddressFactory.createAddressDTO());

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
                var createAddressDTO = AddressFactory.createAddressDTO();
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
                var addressModel = AddressFactory.addressModelWithoutID();
                addressRepository.save(addressModel);

                var updateAddressDTO = AddressFactory.updateAddressDTO();
                String json = objectMapper.writeValueAsString(updateAddressDTO);

                mockMvc.perform(MockMvcRequestBuilders
                                .put("/addresses/" + addressModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateAddress_InvalidID_Error() throws Exception {
                String json = objectMapper.writeValueAsString(AddressFactory.updateAddressDTO());

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
                String json = objectMapper.writeValueAsString(AddressFactory.updateAddressDTO());

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
                var addressModel = AddressFactory.addressModelWithoutID();
                addressRepository.save(addressModel);

                mockMvc.perform(MockMvcRequestBuilders
                                .get("/addresses/" + addressModel.getId().toString())
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
                var addressModel = AddressFactory.addressModelWithoutID();
                addressRepository.save(addressModel);

                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/addresses/" + addressModel.getId().toString())
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
