package com.inventory.inventoryService.Controller;

import com.inventory.inventoryService.Entity.Image;
import com.inventory.inventoryService.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException{
       String imageUrl =imageService.saveImage(file);
       return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id){
       Image image = imageService.getImageById(id);
       return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getContent());
    }

}
