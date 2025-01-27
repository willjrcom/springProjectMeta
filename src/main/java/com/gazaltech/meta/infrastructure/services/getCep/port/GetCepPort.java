package com.gazaltech.meta.infrastructure.services.getCep.port;

public interface GetCepPort<T> {
    T getAddressByZipCode(String cep);
}
