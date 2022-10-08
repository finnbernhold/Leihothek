package com.finnbernhold.leihothek.offer;

import org.springframework.web.bind.annotation.*;

@RestController
public class OfferRestController {
    private final OfferService service;

    public OfferRestController(OfferService service) {
        this.service = service;
    }

    @PostMapping("/api/add")
    public String addOffer(@RequestParam String title, @RequestParam String description){
        service.addOffer(new Offer(null, title, description));
        return "saved book";
    }
    @GetMapping("/api/offer/{id}")
    public Offer showOfferById(@PathVariable Integer id){
        return service.showOfferById(id);
    }
    @GetMapping("/api/show")
    public Iterable<Offer> showAllOffers(){
        return service.showAllOffers();
    }
    @DeleteMapping("/api/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return "deleted";
    }

}
