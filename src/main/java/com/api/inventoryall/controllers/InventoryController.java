package com.api.inventoryall.controllers;

import com.api.inventoryall.dtos.ItemDto;
import com.api.inventoryall.models.ClientModel;
import com.api.inventoryall.models.ItemModel;
import com.api.inventoryall.services.ClientService;
import com.api.inventoryall.services.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/inv-all/")
public class InventoryController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ClientService clientService;

    /**
     * Recupera tdo o conteúdo do inventário.
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping("/inventory")
    public ResponseEntity<Page<ItemModel>> getAllItems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll(pageable));
    }

    /**
     *  Este método Salva um item no inventário.
      * @param itemDto
     * @return ResponseEntity
     */
    @PostMapping("/item")
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDto itemDto){
        if(itemService.existsByQrcode(itemDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O QRCODE já existe");
        }

//        if(itemService.existsByNomeClientAndQrcode(itemDto.getQrcode(), itemDto.getQrcode())){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe este QRCODE para este cliente");
//        }
        var inventoryModel = new ItemModel();
        BeanUtils.copyProperties(itemDto,inventoryModel);
        inventoryModel.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(inventoryModel));
    }

    /**
     * Recupera um único item do inventário.
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") UUID id){
        Optional<ItemModel> inventoryModelOptional = itemService.findById(id);
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
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") UUID id){
        Optional<ItemModel> inventoryModelOptional = itemService.findById(id);
        if (!inventoryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe.");
        }
        itemService.deleteItem(inventoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item excluido!");
    }

    /**
     * Atualiza, se existir, um intem do inventário.
     * @param id
     * @param itemDto
     * @return ResponseEntity
     */
    @PutMapping("/item/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id, @RequestBody @Valid ItemDto itemDto) {
        Optional<ItemModel> inventoryModelOptional = itemService.findById(id);
        if (!inventoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe.");
        }
        var inventoryModel = inventoryModelOptional.get();
        BeanUtils.copyProperties(itemDto,inventoryModel);
        inventoryModel.setId(inventoryModelOptional.get().getId());
        inventoryModel.setDataCadastro(inventoryModelOptional.get().getDataCadastro());
        return ResponseEntity.status(HttpStatus.OK).body(itemService.save(inventoryModel));
    }


    @GetMapping("/clients")
    public ResponseEntity<Page<ClientModel>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "client_id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

}