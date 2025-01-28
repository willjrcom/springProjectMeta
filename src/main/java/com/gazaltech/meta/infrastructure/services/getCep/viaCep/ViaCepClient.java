package com.gazaltech.meta.infrastructure.services.getCep.viaCep;
import org.hibernate.validator.constraints.Length;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep-client", url = "https://viacep.com.br/ws")
@Validated
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    ViaCepResponse getAddressByZipCode(@PathVariable @Length(min = 8, max = 8, message = "Zip Code must be 8 characters") String cep);
}
