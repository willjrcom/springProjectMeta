package com.gazaltech.meta.infrastructure.services.viaCep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ViaCepClientTest {
    @Autowired
    private ViaCepClient viaCepClient;

    @Test
    @DisplayName("Get address by zip code")
    void testGetAddressByZipCode_WithoutDash_Success() {
        var cep = "01001-000";
        var cepWithoutDash = cep.replace("-", "");
        var response = viaCepClient.getAddressByZipCode(cepWithoutDash);

        assertEquals(response.getCep(), cep);
    }

    @Test
    @DisplayName("Get address by zip code with dash")
    void testGetAddressByZipCode_WithDash_Error() {
        var cep = "01001-000";
        assertThrows(ConstraintViolationException.class, () -> viaCepClient.getAddressByZipCode(cep));
    }
}
