package com.api.inventoryall.services;

import com.api.inventoryall.models.InventoryModel;
import com.api.inventoryall.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public InventoryModel save(InventoryModel inventoryModel) {
        return inventoryRepository.save(inventoryModel);
    }
}