package com.gazaltech.meta.models;

import java.util.List;

import com.gazaltech.meta.domain.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "clients")
@AllArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressModel> addresses;

    public Client toDomain() {
        var addresses = this.addresses.stream()
                .map(AddressModel::toDomain)
                .toList();

        return new Client(id, name, email, addresses);
    }

    public static ClientModel toModel(Client client) {
        var addresses = client.getAddresses().stream()
                .map(AddressModel::toModel)
                .toList();

        return new ClientModel(client.getId(), client.getName(), client.getEmail(), addresses);
    }
}
