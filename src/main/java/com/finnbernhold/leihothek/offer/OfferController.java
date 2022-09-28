package com.finnbernhold.leihothek.offer;

import org.springframework.web.bind.annotation.*;

@RestController
public class OfferController {
    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addOffer(@RequestParam String title, @RequestParam String description){
        service.addOffer(title, description);
        return "saved book";
    }
    @GetMapping("/show/{id}")
    public Offer showOfferById(@PathVariable Integer id){
        return service.showOfferById(id);
    }
    @GetMapping("/show")
    public Iterable<Offer> showAllOffers(){
        return service.showAllOffers();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return "deleted";
    }
}
