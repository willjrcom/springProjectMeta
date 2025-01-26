package com.gazaltech.meta.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gazaltech.meta.infrastructure.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

}
