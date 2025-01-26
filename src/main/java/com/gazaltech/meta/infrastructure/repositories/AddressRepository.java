package com.gazaltech.meta.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gazaltech.meta.infrastructure.models.AddressModel;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {

}
