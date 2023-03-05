package com.api.inventoryall.controllers;

import com.api.inventoryall.dtos.InventoryDto;
import com.api.inventoryall.models.InventoryModel;
import com.api.inventoryall.services.InventoryService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/inventory")
public class InventoryController {

    final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     *  Este método Salva um item de inventário no BD.
      * @param inventoryDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Object> saveInventory(@RequestBody @Valid InventoryDto inventoryDto){
        if(inventoryService.existsByQrcode(inventoryDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O QRCODE já existe");
        }
        if(inventoryService.existsByNomeClientAndQrcode(inventoryDto.getNomeCliente(),inventoryDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe este QRCODE para este cliente");
        }
        var inventoryModel = new InventoryModel();
        BeanUtils.copyProperties(inventoryDto,inventoryModel);
        inventoryModel.setRegstrationDAte(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.save(inventoryModel));
    }

    /**
     * Recupera tdo o conteúdo do inventário.
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<Page<InventoryModel>> getAllInventory(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.findAll(pageable));
    }

    /**
     * Recupera um único item do inventário.
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") UUID id){
        Optional<InventoryModel> inventoryModelOptional = inventoryService.findById(id);
        if (!inventoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(inventoryModelOptional.get());
    }

    /**
     * Deleta, se existir, um item do inventário.
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") UUID id){
        Optional<InventoryModel> inventoryModelOptional = inventoryService.findById(id);
        if (!inventoryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe.");
        }
        inventoryService.deleteItem(inventoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item excluido!");
    }

//TODO Método update
}