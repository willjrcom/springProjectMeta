package com.gazaltech.meta.infrastructure.services.getCep.port;

import com.gazaltech.meta.infrastructure.services.getCep.ViaCepResponse;

public interface GetCepPort {
    ViaCepResponse getAddressByZipCode(String zipCode);
}
