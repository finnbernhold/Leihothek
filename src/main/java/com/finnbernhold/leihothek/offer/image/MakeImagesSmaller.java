package com.finnbernhold.leihothek.offer.image;

import com.finnbernhold.leihothek.db.ImageRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class MakeImagesSmaller {
    private final ImageService is;
    private final ImageResize ir;
    private final ImageRepository repository;

    public MakeImagesSmaller(ImageService is, ImageResize ir, ImageRepository repository) {
        this.is = is;
        this.ir = ir;
        this.repository = repository;
    }

    public Integer makeImagesSmaller(int images) throws IOException {
        int asdf = 0;
        for(int i = 1; i <= images; i++){
            Optional<Image> image = is.getImage(i);
            byte[] bImage = image.get().image();
            ir.resizeImage(bImage);
            Image img =  repository.save(new Image(null, bImage, "JSON"));
            return img.id();
        }
        return asdf;
    }

}
