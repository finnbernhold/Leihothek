package com.finnbernhold.leihothek.offer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/newOffer")
    public String newOfferForm(Model model){
        model.addAttribute("blankOffer", new Offer(null, null, null, null));
        return "addOffer";
    }
    @PostMapping("/newOffer")
    public String newOffer(@ModelAttribute Offer newOffer){
        offerService.addOffer(newOffer);
        return "redirect:/";
    }

    @GetMapping("/offer/{id}")
    public String showOfferForm(@PathVariable Integer id, Model model){
        model.addAttribute("offer", offerService.findOfferById(id));
        return "offerView";
    }
}