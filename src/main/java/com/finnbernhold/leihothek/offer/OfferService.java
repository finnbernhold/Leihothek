package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.db.OfferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

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
        return repo.findAllByOrderByIdAsc();
    }
    public void deleteById(Integer id){
        repo.deleteById(id);
    }

    public Iterable<Offer> findAllOwnOffers(Principal principal){
        return repo.findAllOffersOfUser(principal.getName());
    }
    public Iterable<Offer> findAllOffersOfUser(String user){
        return repo.findAllOffersOfUser(user);
    }

    public List<Offer> getFilteredOffers(String query){
        return repo.findByTitleContainingIgnoreCase(query);
    }
}
