package com.inventory.inventoryService.Service;

import com.inventory.inventoryService.Entity.Image;
import com.inventory.inventoryService.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String saveImage(MultipartFile file){

        try {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setContent(file.getBytes());

            Image savedImage = imageRepository.save(image);
            return "/images/" +savedImage.getId();
        }catch (IOException e){
            throw new RuntimeException("failed to save image",e);
        }
    }

    public Image getImageById(Long id){
        Optional<Image> imageOptional= imageRepository.findById(id);
        if (imageOptional.isPresent()){
            return imageOptional.get();
        }else {
            throw new RuntimeException("Image not found");
        }
    }
}
