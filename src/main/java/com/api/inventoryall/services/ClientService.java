package com.api.inventoryall.services;

import com.api.inventoryall.model.Client;
import com.api.inventoryall.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

}
