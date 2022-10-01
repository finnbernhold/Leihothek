package com.finnbernhold.leihothek.db;

import com.finnbernhold.leihothek.offer.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
