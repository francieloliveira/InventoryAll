package com.api.inventoryall.services;

import com.api.inventoryall.model.Item;
import com.api.inventoryall.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public boolean existsByQrcode(String qrcode) {
        return itemRepository.existsByQrcode(qrcode);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }


}
