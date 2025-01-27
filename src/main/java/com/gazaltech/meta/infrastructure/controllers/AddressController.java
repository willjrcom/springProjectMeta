package com.gazaltech.meta.infrastructure.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gazaltech.meta.application.dtos.AddressDTO;
import com.gazaltech.meta.application.dtos.CreateAddressDTO;
import com.gazaltech.meta.application.dtos.UpdateAddressDTO;
import com.gazaltech.meta.application.ports.address.AddressPort;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressPort addressUseCase;

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody @Valid CreateAddressDTO addressDTO) {
        logger.info("Address created: {}", addressDTO);

        var id = addressUseCase.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressByID(@PathVariable Long id) {
        logger.info("Address found: {}", id);

        var addressDTO = addressUseCase.getAddressByID(id);
        return ResponseEntity.ok(addressDTO);
    }

    @GetMapping("/zip-code/{zipCode}")
    public ResponseEntity<List<AddressDTO>> getAddressesByZipCode(@PathVariable String zipCode) {
        logger.info("Address found: {}", zipCode);

        var addressDTO = addressUseCase.getAddressesByZipCode(zipCode);
        return ResponseEntity.ok(addressDTO);
    }

    @GetMapping("/search-zip-code/{zipCode}")
    public ResponseEntity<AddressDTO> searchCep(@PathVariable String zipCode) {
        logger.info("Address found: {}", zipCode);

        var addressDTO = addressUseCase.searchZipCode(zipCode);
        return ResponseEntity.ok(addressDTO);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses(@RequestParam int page, @RequestParam int size) {
        logger.info("Addresses found");

        var addresses = addressUseCase.getAllAddresses(page, size);
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable Long id,
            @RequestBody @Valid UpdateAddressDTO addressDTO) {
        logger.info("Address updated: {}", id);

        addressUseCase.updateAddress(id, addressDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressByID(@PathVariable Long id) {
        logger.info("Address deleted: {}", id);

        addressUseCase.deleteAddressByID(id);
        return ResponseEntity.noContent().build();
    }
}
