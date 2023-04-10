package com.finnbernhold.leihothek.offer.image;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageResize {
    byte[] resizeImage(byte[] image) throws IOException {

        InputStream inputStream = new ByteArrayInputStream(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(inputStream)
                .width(1000)
                .keepAspectRatio(true)
                .outputFormat("JPEG")
                .outputQuality(0.5)
                .useExifOrientation(true)
                .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }
}