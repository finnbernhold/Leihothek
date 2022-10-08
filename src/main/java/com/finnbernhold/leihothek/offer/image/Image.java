package com.finnbernhold.leihothek.offer.image;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("image")
public record Image(
        @Id
        Integer id,
        String title,
        byte[] image,
        String mimeType
) {}
