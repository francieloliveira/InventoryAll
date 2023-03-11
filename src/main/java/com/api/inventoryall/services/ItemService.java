package com.api.inventoryall.services;

import com.api.inventoryall.models.ItemModel;
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

    public Page<ItemModel> findAll(Pageable pageable) { return itemRepository.findAll(pageable); }

    public Optional<ItemModel> findById(UUID id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public ItemModel save(ItemModel itemModel) {
        return itemRepository.save(itemModel);
    }

    @Transactional
    public void deleteItem(ItemModel itemModel) { itemRepository.delete(itemModel);}


}
