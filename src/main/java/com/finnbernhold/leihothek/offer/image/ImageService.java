package com.finnbernhold.leihothek.offer.image;

import com.finnbernhold.leihothek.db.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository repository) {
        this.imageRepository = repository;
    }

    public Integer addImage(String title, MultipartFile file) throws IOException {
        Image img = new Image(null, title, file.getBytes(), file.getContentType());
        img = imageRepository.save(img);
        return img.id();
    }

    public Optional<Image> getImage(Integer id) {
        return imageRepository.findById(id);
    }
    public void deleteById(Integer id){
        imageRepository.deleteById(id);
    }
}