package com.finnbernhold.leihothek.offer;

import org.springframework.web.bind.annotation.*;

@RestController
public class OfferRestController {
    private final OfferService service;

    public OfferRestController(OfferService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addOffer(@RequestParam String title, @RequestParam String description){
        service.addOffer(new Offer(null, title, description));
        return "saved book";
    }
    @GetMapping("/offer/{id}")
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
