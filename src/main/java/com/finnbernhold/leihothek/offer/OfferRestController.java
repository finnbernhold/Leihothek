package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.offer.image.ImageService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfferRestController {
    private final OfferService offerService;
    private final ImageService imageService;

    public OfferRestController(OfferService offerService, ImageService imageService) {
        this.offerService = offerService;
        this.imageService = imageService;
    }

    @PostMapping("/api/add")
    public String addOffer(@RequestParam String title, @RequestParam String description){
        offerService.addOffer(new Offer(null, title, description, null));
        return "saved book";
    }
    @GetMapping("/api/offer/{id}")
    public Offer showOfferById(@PathVariable Integer id){
        return offerService.findOfferById(id);
    }
    @GetMapping("/api/show")
    public Iterable<Offer> showAllOffers(){
        return offerService.findAllOffers();
    }
    @DeleteMapping("/api/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        imageService.deleteById(offerService.findOfferById(id).imageId());
        offerService.deleteById(id);
        return "deleted";
    }

}
