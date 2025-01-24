package com.gazaltech.meta.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gazaltech.meta.domain.Address;
import com.gazaltech.meta.domain.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AddressModel> addresses = new ArrayList<AddressModel>();

    public Client toDomain() {
        List<Address> addresses = this.addresses.stream()
                .map(AddressModel::toDomain)
                .collect(Collectors.toList());

        return new Client(id, name, email, cpf, addresses);
    }

    public static ClientModel toModel(Client client) {
        var addresses = Optional.ofNullable(client.getAddresses())
                .map(list -> list.stream().map(AddressModel::toModel).collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        return new ClientModel(client.getId(), client.getName(), client.getEmail(), client.getCpf(), addresses);
    }
}
