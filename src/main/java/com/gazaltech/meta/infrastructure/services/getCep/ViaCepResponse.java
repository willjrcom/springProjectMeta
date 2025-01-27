package com.gazaltech.meta.infrastructure.services.getCep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ViaCepResponse {
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf; 
}

