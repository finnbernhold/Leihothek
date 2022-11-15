package com.finnbernhold.leihothek.offer.image;



import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageResize {
    byte[] resizeImage(byte[] image) throws IOException {

        InputStream inputStream = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalImage)
                .size(350, 350)
                .outputFormat("JPEG")
                .outputQuality(1)
                .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }
}