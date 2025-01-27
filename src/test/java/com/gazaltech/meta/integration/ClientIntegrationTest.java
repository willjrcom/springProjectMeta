package com.gazaltech.meta.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gazaltech.meta.factories.AddressFactory;
import com.gazaltech.meta.factories.ClientFactory;
import com.gazaltech.meta.infrastructure.repositories.AddressRepository;
import com.gazaltech.meta.infrastructure.repositories.ClientRepository;

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
public class ClientIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        private AddressRepository addressRepository;

        @BeforeEach
        void up() {
                clientRepository.save(ClientFactory.clientModel);
                addressRepository.save(AddressFactory.addressModel);
        }

        @AfterEach
        void down() {
                clientRepository.deleteAll();
        }

        @Test
        void createClient_AllFields_Success() throws Exception {
                String json = objectMapper.writeValueAsString(ClientFactory.createClientDTO);

                mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isCreated())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void createClient_WithoutName_Error() throws Exception {
                ClientFactory.createClientDTO.setName(null);
                String json = objectMapper.writeValueAsString(ClientFactory.createClientDTO);

                mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateClient_AllFields_Success() throws Exception {
                String json = objectMapper.writeValueAsString(ClientFactory.updateClientDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/clients/" + ClientFactory.clientModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateClient_InvalidID_Error() throws Exception {
                String json = objectMapper.writeValueAsString(ClientFactory.updateClientDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/clients/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void updateClient_NotFoundID_Error() throws Exception {
                String json = objectMapper.writeValueAsString(ClientFactory.updateClientDTO);

                mockMvc.perform(MockMvcRequestBuilders.put("/clients/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json")
                                .content(json))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getClientByID_ValidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/clients/" + ClientFactory.clientModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isOk())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getClientByID_InvalidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/clients/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void getClientByID_NotFoundID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/clients/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteClientByID_ValidID_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/clients/" + ClientFactory.clientModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteClientByID_InvalidID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/clients/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void deleteClientByID_NotFoundID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.delete("/clients/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void addAddressToClient_ValidIDs_Success() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/clients/" + ClientFactory.clientModel.getId().toString() + "/address/"
                                                + AddressFactory.addressModel.getId().toString())
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void addAddressToClient_InvalidIDs_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/clients/invalidID/address/invalidID")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void addAddressToClient_NotFoundID_Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders
                                .post("/clients/999/address/999")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void removeAddressFromClient_ValidID_Success() throws Exception {
                ClientFactory.clientModel.setAddress(AddressFactory.addressModel);
                clientRepository.save(ClientFactory.clientModel);

                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/clients/" + ClientFactory.clientModel.getId().toString() + "/address")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNoContent())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void removeAddressFromClient_InvalidID_Error() throws Exception {
                ClientFactory.clientModel.setAddress(AddressFactory.addressModel);
                clientRepository.save(ClientFactory.clientModel);

                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/clients/invalidID/address")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isBadRequest())
                                .andDo(MockMvcResultHandlers.print());
        }

        @Test
        void removeAddressFromClient_NotFoundID_Error() throws Exception {
                ClientFactory.clientModel.setAddress(AddressFactory.addressModel);
                clientRepository.save(ClientFactory.clientModel);

                mockMvc.perform(MockMvcRequestBuilders
                                .delete("/clients/999/address")
                                .contentType("application/json")
                                .characterEncoding("UTF-8")
                                .accept("application/json"))
                                .andExpect(status().isNotFound())
                                .andDo(MockMvcResultHandlers.print());
        }
}
