package com.gazaltech.meta.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Address address;
}
