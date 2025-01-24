package com.gazaltech.meta.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gazaltech.meta.models.ClientModel;

@Repository
public interface ClientRepositoryImpl extends JpaRepository<ClientModel, Long> {

}
