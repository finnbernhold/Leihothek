package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.db.OfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OfferService {
    private final OfferRepository repo;

    public OfferService(OfferRepository repo) {
        this.repo = repo;
    }

    public void addOffer(String title, String description){
        repo.save(new Offer(null, title, description));
    }
    public Offer showOfferById(Integer id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer not found"));
    }
    public Iterable<Offer> showAllOffers(){
        return repo.findAll();
    }
    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
