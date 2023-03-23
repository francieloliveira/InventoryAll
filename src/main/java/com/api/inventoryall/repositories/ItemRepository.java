package com.api.inventoryall.repositories;

import com.api.inventoryall.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    boolean existsByQrcode (String qrcode);
}
