package com.api.inventoryall.controllers;

import com.api.inventoryall.dtos.InventoryDto;
import com.api.inventoryall.models.InventoryModel;
import com.api.inventoryall.services.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/inventory")
public class InventoryController {

    final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveInventory(@RequestBody @Valid InventoryDto inventoryDto){
        if(inventoryService.existsByQrcode(inventoryDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O QRCODE já existe");
        }
        if(inventoryService.existsByClientAndQrCode(inventoryDto.getNomeCliente(),inventoryDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe este QRCODE para este cliente");
        }
        var inventoryModel = new InventoryModel();
        BeanUtils.copyProperties(inventoryDto,inventoryModel);
        inventoryModel.setRegstrationDAte(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(inventoryModel));
    }
}
