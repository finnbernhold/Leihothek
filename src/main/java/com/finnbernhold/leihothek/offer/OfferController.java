package com.finnbernhold.leihothek.offer;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/newOffer")
    public String newOfferForm(Model model){
        model.addAttribute("blankOffer", new Offer(null, null, null, null, null));
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

    @GetMapping("/")
    public String showAllOffers(Model model, @RequestParam(required = false) String query){
        if (query == null){
            Iterable<Offer> allOffers = offerService.findAllOffers();
            model.addAttribute("count", IterableUtils.size(allOffers));
            model.addAttribute("offers", allOffers);
        }else {
            model.addAttribute("query", query);
            List<Offer> filteredOffers = offerService.getFilteredOffers(query);
            model.addAttribute("count", filteredOffers.size());
            model.addAttribute("offers", filteredOffers);
        }
        return "start";
    }

    @GetMapping("/ownOffers")
    public String showAllOwnOffers(Model model, Principal principal){
        model.addAttribute("offersOfUser", offerService.findAllOwnOffers(principal));
        return "offersOfUser";
    }
    @GetMapping("/user/{userName}")
    public String showAllOffersOfUser(Model model, @PathVariable String userName){
        model.addAttribute("offersOfUser", offerService.findAllOffersOfUser(userName));
        return "offersOfUser";
    }


}