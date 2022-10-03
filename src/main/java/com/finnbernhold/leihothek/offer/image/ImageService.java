package com.finnbernhold.leihothek.offer.image;

import com.finnbernhold.leihothek.db.ImageRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Integer addImage(MultipartFile file) throws IOException {
        Image img = new Image(null, file.getBytes(), file.getContentType());
        img = imageRepository.save(img);
        return img.id();
    }
}
