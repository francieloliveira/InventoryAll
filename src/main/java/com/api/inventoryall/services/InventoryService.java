package com.api.inventoryall.services;

import com.api.inventoryall.models.InventoryModel;
import com.api.inventoryall.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public InventoryModel save(InventoryModel inventoryModel) {
        return inventoryRepository.save(inventoryModel);
    }

    public boolean existsByQrcode(String qrcode) {
        return inventoryRepository.existsByQrcode(qrcode);
    }

    public boolean existsByNomeClientAndQrcode(String nomeCliente, String qrcode) {
        return inventoryRepository.existsByNomeClienteAndQrcode(nomeCliente, qrcode);
    }

    public Page<InventoryModel> findAll(Pageable pageable) { return inventoryRepository.findAll(pageable); }

    public Optional<InventoryModel> findById(UUID id) {
        return inventoryRepository.findById(id);
    }

    @Transactional
    public void deleteItem(InventoryModel inventoryModel) { inventoryRepository.delete(inventoryModel);}


}
