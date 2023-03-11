package com.api.inventoryall.repositories;

import com.api.inventoryall.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, UUID> {
    boolean existsByQrcode (String qrcode);
}
