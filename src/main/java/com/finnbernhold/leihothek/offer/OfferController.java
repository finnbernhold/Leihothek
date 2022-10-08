package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.offer.image.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final ImageService imageService;

    public OfferController(OfferService offerService, ImageService imageService) {
        this.offerService = offerService;
        this.imageService = imageService;
    }

    @GetMapping("/newOffer")
    public String newOfferForm(Model model){
        model.addAttribute("blankOffer", new Offer(null, null, null));
        return "addOffer";
    }
    @PostMapping("/newOffer")
    public String newOffer(@ModelAttribute Offer newOffer){
        offerService.addOffer(newOffer);
        return "redirect:/";
    }
}