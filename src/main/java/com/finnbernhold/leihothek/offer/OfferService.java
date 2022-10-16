package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.db.OfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OfferService {
    private final OfferRepository repo;

    public OfferService(OfferRepository repo) {
        this.repo = repo;
    }

    public void addOffer(Offer offer){
        repo.save(offer);
    }
    public Offer findOfferById(Integer id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found"));
    }
    public Iterable<Offer> findAllOffers(){
        return repo.findAll();
    }
    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
