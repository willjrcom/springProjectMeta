package com.gazaltech.meta.infrastructure.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.gazaltech.meta.application.dtos.ClientDTO;
import com.gazaltech.meta.application.dtos.CreateClientDTO;
import com.gazaltech.meta.application.dtos.UpdateClientDTO;
import com.gazaltech.meta.application.usecases.ClientUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientUseCase clientUseCase;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody @Valid CreateClientDTO clientDTO) {
        logger.info("Client created: {}", clientDTO);

        var id = clientUseCase.createClient(clientDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientByID(@PathVariable Long id) {
        logger.info("Client found: {}", id);

        var client = clientUseCase.getClientByID(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(@RequestParam int page, @RequestParam int size) {
        logger.info("Clients found");

        var clients = clientUseCase.getAllClients(page, size);
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<String> addAddress(@PathVariable Long id, @RequestBody @Valid Long addressId) {
        logger.info("Address added: {}", addressId);

        clientUseCase.addAddress(id, addressId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/addresses/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable Long id, @PathVariable Long addressId) {
        logger.info("Address removed: {}", addressId);

        clientUseCase.removeAddress(id, addressId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid UpdateClientDTO clientDTO) {
        logger.info("Client updated: {}", id);

        clientUseCase.updateClient(id, clientDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientByID(@PathVariable Long id) {
        logger.info("Client deleted: {}", id);

        clientUseCase.deleteClientByID(id);
        return ResponseEntity.noContent().build();
    }
}
