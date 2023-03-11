package com.api.inventoryall.services;

import com.api.inventoryall.models.ClientModel;
import com.api.inventoryall.models.ItemModel;
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

    public Page<ClientModel> findAll(Pageable pageable) { return clientRepository.findAll(pageable); }

    public Optional<ClientModel> findById(UUID id) { return clientRepository.findById(id); }

    @Transactional
    public ClientModel save(ClientModel clientModel) { return clientRepository.save(clientModel); }

    @Transactional
    public void delete(UUID id) { clientRepository.deleteById(id); }

    @Transactional
    public ClientModel update(ClientModel clientModel) { return clientRepository.save(clientModel); }


}
