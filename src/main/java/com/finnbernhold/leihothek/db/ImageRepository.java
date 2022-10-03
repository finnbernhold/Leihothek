package com.finnbernhold.leihothek.db;

import com.finnbernhold.leihothek.offer.image.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
