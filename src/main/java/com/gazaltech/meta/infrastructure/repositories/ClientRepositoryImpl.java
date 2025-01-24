package com.gazaltech.meta.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gazaltech.meta.models.ClientModel;

public interface ClientRepositoryImpl extends JpaRepository<ClientModel, Long> {

}
