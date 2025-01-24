package com.gazaltech.meta.models;

import com.gazaltech.meta.domain.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = true) 
    private AddressModel address;

    public Client toDomain() {
        return new Client(id, name, email, cpf, address == null ? null : address.toDomain());
    }

    public static ClientModel toModel(Client client) {
        return new ClientModel(client.getId(), client.getName(), client.getEmail(), client.getCpf(), AddressModel.toModel(client.getAddress()));
    }
}
