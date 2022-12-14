package com.finnbernhold.leihothek.offer;

import com.finnbernhold.leihothek.offer.image.ImageService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

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
        model.addAttribute("blankOffer", new Offer(null,null, null, null, null, null));
        return "addOffer";
    }
    @PostMapping("/newOffer")
    public String newOffer(@ModelAttribute Offer newOffer){
        offerService.addOffer(newOffer);
        return "redirect:/ownOffers";
    }

    @GetMapping("/offer/{id}")
    public String showOfferForm(@PathVariable Integer id, Model model){
        model.addAttribute("offer", offerService.findOfferById(id));
        return "offerView";
    }

    @GetMapping("/")
    public String showAllOffers(Model model, @RequestParam(required = false) String query, @RequestParam(required = false) String category) {
        System.out.println(category + query);
        if (category == null || category.equals("Alle")) {
            if (query == null) {
                Iterable<Offer> allOffers = offerService.findAllOffers();
                model.addAttribute("count", IterableUtils.size(allOffers));
                model.addAttribute("offers", allOffers);
            } else {
                model.addAttribute("query", query);
                List<Offer> filteredOffers = offerService.findOffersByTitle(query);
                model.addAttribute("count", filteredOffers.size());
                model.addAttribute("offers", filteredOffers);
            }
        } else {
            try {
                Categories categoryEnum = Categories.valueOf(category);
                if (query == null) {
                    Iterable<Offer> allOffers = offerService.findOffersByCategory(categoryEnum);
                    model.addAttribute("count", IterableUtils.size(allOffers));
                    model.addAttribute("offers", allOffers);
                } else {
                    model.addAttribute("query", query);
                    List<Offer> filteredOffers = offerService.findOffersByTitleAndCategory(categoryEnum, query);
                    model.addAttribute("count", filteredOffers.size());
                    model.addAttribute("offers", filteredOffers);
                    model.addAttribute("selectedCategory", categoryEnum);
                }

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Keine Kategorie");

            }
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

    @PostMapping("/offer/{id}/delete")
    public String deleteOffer(@PathVariable Integer id) {
        Offer offer = offerService.findOfferById(id);
        if (offer.imageId() != null) {
            imageService.deleteById(offerService.findOfferById(id).imageId());

        }
        offerService.deleteById(id);
        return "redirect:/ownOffers";
    }

    @GetMapping("offer/{id}/edit")
    public String editOfferForm(Model model, @PathVariable Integer id) {
        model.addAttribute("editOffer", offerService.findOfferById(id));
        return "editOffer";
    }

    @PostMapping("offer/{id}/edit")
    public String editOffer(@ModelAttribute Offer editedOffer) {
        offerService.saveEditedOffer(editedOffer);
        return "redirect:/ownOffers";
    }
}