package com.gazaltech.meta.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.infrastructure.controllers.ClientController;
import com.gazaltech.meta.application.ports.client.ClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
public class CreateClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientPort clientUseCase;

    private CreateClientDTO createClientDTO;

    @BeforeEach
    void setUp() {
        // Configura o DTO de entrada
        createClientDTO = CreateClientDTO.builder()
                .cpf("436.377.998-55")
                .email("will@gmail.com")
                .name("John Doe")
                .build();

        // Configura o mock para o caso de uso// Caso o método retorne um objeto
        Mockito.when(clientUseCase.createClient(Mockito.any(CreateClientDTO.class)))
                .thenReturn("mock-client-id");

    }

    @Test
    void shouldCreateClientSuccessfully() throws Exception {
        // Serializa o DTO para JSON
        String json = objectMapper.writeValueAsString(createClientDTO);

        // Simula a requisição POST
        mockMvc.perform(MockMvcRequestBuilders.post("/clients")
                .contentType("application/json")
                .accept("application/json")
                .content(json))
                .andExpect(status().isOk());

        // Verifica se o método do caso de uso foi chamado corretamente
        Mockito.verify(clientUseCase, Mockito.times(1)).createClient(Mockito.any(CreateClientDTO.class));
    }
}
