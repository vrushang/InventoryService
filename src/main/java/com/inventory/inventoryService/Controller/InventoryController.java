package com.inventory.inventoryService.Controller;

import com.inventory.inventoryService.Entity.Inventory;
import com.inventory.inventoryService.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/getAllItem")
    public ResponseEntity<List<Inventory>> getAllInventory(){
        List<Inventory> inventoryList =  inventoryService.getAllInventory();

        return  ResponseEntity.ok().body(inventoryList);
    }

    @PostMapping(value = "/addItem")
    public ResponseEntity<Inventory> addInventoryItem(@RequestBody Inventory inventory){
        Inventory newInventory= inventoryService.addInventory(inventory);

        return ResponseEntity.status(HttpStatus.CREATED).body(newInventory);
    }

    @GetMapping("/getItemById/{id}")
    public  ResponseEntity<Inventory> getInventoryById(@PathVariable Long id){
        Optional<Inventory> inventoryOptional =inventoryService.getInventoryById(id);
        if(inventoryOptional.isPresent()){
            Inventory inventory= inventoryOptional.get();
            return  ResponseEntity.ok().body(inventory);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateItem/{id}")
    public ResponseEntity<Inventory> updateInventoryById(@PathVariable long id, @RequestBody Inventory inventory){
        Optional<Inventory> inventoryOptional= inventoryService.getInventoryById(id);

        if (inventoryOptional.isPresent()){
            Inventory existingInventory =inventoryOptional.get();
            existingInventory.setName(inventory.getName());
            existingInventory.setPrice(inventory.getPrice());
            existingInventory.setQuantity(inventory.getQuantity());

            Inventory updatedInventory = inventoryService.updateInventory(existingInventory);
            return  ResponseEntity.ok().body(updatedInventory);
        }else {
            return  ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<?> deleteInventoryById(@PathVariable Long id){
        Optional<Inventory> inventoryOptional= inventoryService.getInventoryById(id);
        if (inventoryOptional.isPresent()) {
            inventoryService.deleteInventory(id);
            return ResponseEntity.ok().build();
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }

}
