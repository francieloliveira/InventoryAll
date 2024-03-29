package com.api.inventoryall.repositories;

import com.api.inventoryall.models.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, UUID> {
    boolean existsByQrcode (String qrcode);
    boolean existsByNomeClienteAndQrcode (String nomeCliente, String qrcode);
}
