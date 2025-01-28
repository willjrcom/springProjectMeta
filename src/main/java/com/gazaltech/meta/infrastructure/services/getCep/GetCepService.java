package com.gazaltech.meta.infrastructure.services.getCep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazaltech.meta.infrastructure.services.getCep.port.GetCepPort;
import com.gazaltech.meta.infrastructure.services.getCep.viaCep.ViaCepClient;
import com.gazaltech.meta.infrastructure.services.getCep.viaCep.ViaCepResponse;

@Service
public class GetCepService implements GetCepPort {

    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public ViaCepResponse getAddressByZipCode(String cep) {
        return viaCepClient.getAddressByZipCode(cep);
    }
}
