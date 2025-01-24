package com.gazaltech.meta.infrastructure.services.viaCep;

import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Uf;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViaCepResponse {
        private String cep;
        private String logradouro;
        private String bairro;
        private String localidade;
        private String uf; 

        public void updateDomain(Address address) {
            if (cep == null) {
                return;
            }

            address.setStreet(logradouro);
            address.setCity(localidade);
            address.setNeighborhood(bairro);
            address.setUf(Uf.valueOf(uf));
        }
}

