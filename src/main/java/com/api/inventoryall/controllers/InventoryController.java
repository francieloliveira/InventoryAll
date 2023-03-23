package com.api.inventoryall.controllers;

import com.api.inventoryall.dtos.ClientDTO;
import com.api.inventoryall.dtos.ItemDTO;
import com.api.inventoryall.model.Client;
import com.api.inventoryall.model.Item;
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
@RequestMapping("/inventory")
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
    @GetMapping("/all-items")
    public ResponseEntity<Page<Item>> getAllItems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll(pageable));
    }

    /**
     * Recupera um único item do inventário.
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") UUID id){
        Optional<Item> inventoryModelOptional = itemService.findById(id);
        if (!inventoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(inventoryModelOptional.get());
    }

    /**
     *  Este método Salva um item no inventário.
      * @param itemDto
     * @return ResponseEntity
     */
    @PostMapping("/save-item")
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDTO itemDto){
        if(itemService.existsByQrcode(itemDto.getQrcode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O QRCODE já existe");
        }
        var itemModel = new Item();
        BeanUtils.copyProperties(itemDto,itemModel);
        itemModel.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemModel));
    }

    /**
     * Atualiza, se existir, um intem do inventário.
     * @param id
     * @param itemDto
     * @return ResponseEntity
     */
    @PutMapping("/update-item/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id, @RequestBody @Valid ItemDTO itemDto) {
        Optional<Item> inventoryModelOptional = itemService.findById(id);
        if (!inventoryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe.");
        }
        var inventoryModel = inventoryModelOptional.get();
        BeanUtils.copyProperties(itemDto,inventoryModel);
        inventoryModel.setId(inventoryModelOptional.get().getId());
        inventoryModel.setDataCadastro(inventoryModelOptional.get().getDataCadastro());
        return ResponseEntity.status(HttpStatus.OK).body(itemService.save(inventoryModel));
    }

    /**
     * Deleta, se existir, um item do inventário.
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/del-item/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") UUID id){
        Optional<Item> itemOptional = itemService.findById(id);
        if (!itemOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não existe.");
        }
        itemService.deleteItem(itemOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item excluido!");
    }



    /**
     * Recupera tdos os clientes do inventário.
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping("/all-clients")
    public ResponseEntity<Page<Client>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

    /**
     * Recupera um único cliente do inventário.
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable(value = "id") UUID id){
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
    }

    /**
     * Salva um client no BD
     * @param clientDTO
     * @return
     */
    @PostMapping("/save-client")
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDTO clientDTO){
        if(clientService.existsByEmail(clientDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: O email já existe");
        }
        var clientModel = new Client();
        BeanUtils.copyProperties(clientDTO,clientModel);
        clientModel.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    /**
     * Atualiza, se existir, um cliente.
     * @param id
     * @param clientDto
     * @return ResponseEntity
     */
    @PutMapping("/update-client/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDTO clientDto) {
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client não existe.");
        }
        var clientModel = clientOptional.get();
        BeanUtils.copyProperties(clientDto,clientModel);
        clientModel.setId(clientOptional.get().getId());
        clientModel.setDataCadastro(clientOptional.get().getDataCadastro());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }

    /**
     * Deleta, se existir, um client do BD.
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/del-client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id){
        Optional<Client> clientOptional = clientService.findById(id);
        if (!clientOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe.");
        }
        clientService.deleteClient(clientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client excluido!");
    }


}