package com.inventory.inventoryService.Service;

import com.inventory.inventoryService.Entity.Inventory;
import com.inventory.inventoryService.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;


    public List<Inventory> getAllInventory(){
        return  (List<Inventory>) inventoryRepository.findAll();
    }

    public  Inventory addInventory(Inventory inventory){
        return  inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getInventoryById(Long id){
        return  inventoryRepository.findById(id);
    }

    public Inventory updateInventory(Inventory inventory){
        return  inventoryRepository.save(inventory);
    }

    public  void deleteInventory(Long id){
        inventoryRepository.deleteById(id);
    }
}
