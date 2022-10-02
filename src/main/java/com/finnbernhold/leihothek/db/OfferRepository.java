package com.finnbernhold.leihothek.db;

import com.finnbernhold.leihothek.offer.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
    List<Offer> findAllByOrderByIdAsc();
}
